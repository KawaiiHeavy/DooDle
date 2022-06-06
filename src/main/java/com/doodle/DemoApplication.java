package com.doodle;

import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.TestService;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Component
class DoodleCommandLineRunner implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    TestService testService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Creating roles
        roleCreation();

        testCreation(10);

        Test test = (Test)testService.findTests("test1").toArray()[0];
        System.out.println(test);

//        testService.deleteTest(test);
    }

    public void roleCreation(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ADMIN));
        roles.add(new Role(ERole.TRAINER));
        roles.add(new Role(ERole.STUDENT));
        roles.add(new Role(ERole.USER));

        roles.stream().filter(role -> !roleRepository.existsByName(role.getName())).forEach(role -> roleRepository.save(role));
    }

    public Set<User> userCreation(int count){

        Set<User> users = new HashSet<>();

        for (int i = 0; i < count; ++i){

            String prefix = getPrefixForCreation();

            User user = new User(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new StringBuffer(prefix).append(i).append("@gmail.com").toString(),
                    new StringBuffer(prefix).append(i).toString(),
                    getRandomPhone(),
                    null,
                    convertRoleToPrivileges(prefix.toUpperCase()));

            users.add(user);
        }
        return users;
    }

    public String getPrefixForCreation(){
        Random random = new Random();
        int choice = random.nextInt(4);
        String prefix;

        switch (choice){
            case 0:
                return "user";
            case 1:
                return "admin";
            case 2:
                return "trainer";
            case 3:
                return "student";
        }
        return "user";
    }

    public String getRandomPhone(){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 10; i++){
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public Set<Role> convertRoleToPrivileges(String role){
        Set<Role> privileges = new HashSet<>();
        switch (role){
            case "ADMIN":
                privileges.add(roleRepository.findByName(ERole.ADMIN).get());

            case "TRAINER":
                privileges.add(roleRepository.findByName(ERole.TRAINER).get());

            case "STUDENT":
                privileges.add(roleRepository.findByName(ERole.STUDENT).get());

            default:
                privileges.add(roleRepository.findByName(ERole.USER).get());
                return privileges;
        }
    }

    public void testCreation(int count){
        String prefix = "test";

        Set<User> members = userCreation(10);
        Set<Result> results = resultsCreation(10);

        User leader = (User)members.toArray()[0];

        System.out.println(leader);

        for (int i = 0; i < count; ++i){
            Test test = new Test();

            test.setId(UUID.randomUUID());
            test.setMaxBall((double) new Random().nextInt(100));
            test.setSeconds(new Random().nextInt(100));
            test.setMembers(members);
            test.setCreator(leader);
            test.setTitle(new StringBuffer(prefix).append(i).toString());
            test.setQuestions(questionCreation(10, 10));

            testService.createTest(test);

            for (Result result: results){
                result.setParticipant(leader);
                test.addResult(result);
            }

            testService.createTest(test);

        }

    }

    public Set<Question> questionCreation(int questionCount, int answerCount){
        String prefix = "question";

        Set<Question> questions = new HashSet<>();
        Set<Answer> answers = answerCreation(answerCount);

        for (int i = 0; i < questionCount; ++i){
            UUID questionId = UUID.randomUUID();
            Question question = new Question(
                    questionId,
                    new StringBuffer(prefix).append(i).toString(),
                    15 * new Random().nextDouble());
            for (Answer answer: answers){
                question.addAnswer(answer);
            }
            questions.add(question);
        }
        return questions;
    }

    public Set<Answer> answerCreation(int count){
        String prefix = "answer";
        Set<Answer> answers = new HashSet<>();
        for (int i = 0; i < count; ++i){
            answers.add(new Answer(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new Random().nextBoolean()
            ));
        }
        return answers;
    }

    public Question getQuestion1(){
        Question question = new Question();
        question.setQuestionText("Для каких объектов маршрутизатор выбирает маршрут?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Для пакетов третьего уровня", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Для фреймов второго уровня", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Для дейтаграмм второго уровня", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Для битов первого уровня", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Для сегментов четвертого уровня", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion2(){
        Question question = new Question();
        question.setQuestionText("Какие устройства сети получают ARP запрос (ARP Request)?");
        question.setScoreWeight(15.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос", true));
        answers.add(new Answer(UUID.randomUUID(),
                "только компьютеры той локальной сети, которой принадлежит хост, пославший ARP запрос", false));
        answers.add(new Answer(UUID.randomUUID(),
                "только порты коммутаторов той локальной сети, которой принадлежит хост, пославший ARP запрос", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос, и порты маршрутизаторов соседних сетей", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion3(){
        Question question = new Question();
        question.setQuestionText("Какое значение параметра Cipher Type в настройках точки доступа D-Link DWL-2100AP использовалось при выполнении лабораторной работы?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "AES", true));
        answers.add(new Answer(UUID.randomUUID(),
                "TKIP", false));
        answers.add(new Answer(UUID.randomUUID(),
                "AUTO", false));
        answers.add(new Answer(UUID.randomUUID(),
                "CCMP", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion4(){
        Question question = new Question();
        question.setQuestionText("Точка доступа установлена на потолке Вашего офиса и подключена. Какой параметр должен быть (как минимум) настроен на точке доступа, чтобы обеспечить взаимодействие с ней беспроводных клиентов?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "SSID", true));
        answers.add(new Answer(UUID.randomUUID(),
                "WEP", false));
        answers.add(new Answer(UUID.randomUUID(),
                "AES", false));
        answers.add(new Answer(UUID.randomUUID(),
                "TKIP", false));
        answers.add(new Answer(UUID.randomUUID(),
                "PSK", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion5(){
        Question question = new Question();
        question.setQuestionText("При выборе параметра (channel - канал) при настройке беспроводной точки доступа стандарта IEEE 802.11g следует задавать номер канала так, чтобы он отличался от номеров каналов соседних точек доступа с мощным сигнала не менее чем на:");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "4", true));
        answers.add(new Answer(UUID.randomUUID(),
                "1", false));
        answers.add(new Answer(UUID.randomUUID(),
                "2", false));
        answers.add(new Answer(UUID.randomUUID(),
                "3", false));
        answers.add(new Answer(UUID.randomUUID(),
                "8", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion6(){
        Question question = new Question();
        question.setQuestionText("Одна точка доступа 802.11g была настроена и установлена в центре квадратного офиса. Некоторые пользователи испытывают замедление в связи и потерю пакетов, в то время, как большинство пользователей работают с сетью в полную силу. В чем может быть проблема?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Помехи от беспроводных телефонов", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Мешают металлические шкафы", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Неподходящие антенны или неверное их направление/расположение", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Не настроен SSID", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Неверно настроен SSID", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion7(){
        Question question = new Question();
        question.setQuestionText("В чем состоит преимущество дистанционно-векторных алгоритмов маршрутизации (DVA)?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Эти протоколы просты в вычислительном отношении", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Эти протоколы легко реализуются в крупных сетях", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Для них маловероятно бесконечное накапливание количества переходов(зацикливание в кольцевом маршруте)", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Хорошо работают в сетях любых размеров", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion8(){
        Question question = new Question();
        question.setQuestionText("Где предпочтительнее всего размещать расширенные списки управления доступом (ACL)?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Как можно ближе к отправителям трафика", true));
        answers.add(new Answer(UUID.randomUUID(),
                "В сети Internet", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Ни в одном из перечисленных выше мест", false));
        answers.add(new Answer(UUID.randomUUID(),
                "На магистральных каналах сети", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion9(){
        Question question = new Question();
        question.setQuestionText("В чем цель использования команды traceroute?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Проверить, какой маршрут пройдет пакет по пути к точке назначения", true));
        answers.add(new Answer(UUID.randomUUID(),
                "Создать карту устройств в сети", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Показать текущие настройки TCP/IP", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Проверить какой MAC-адрес устройства соответсвует его IP-адресу", false));
        answers.add(new Answer(UUID.randomUUID(),
                "Показать значение MTU для каждого маршрутизатора в заданном маршруте до точки назначения", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Question getQuestion10(){
        Question question = new Question();
        question.setQuestionText("Сколько неперекрывающихся каналов можно организовать в диапазоне 2,4 ГГц при использовании стандарта IEEE 802.11a?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        Set<Answer> answers = new HashSet<>();
        answers.add(new Answer(UUID.randomUUID(),
                "Ни одного", true));
        answers.add(new Answer(UUID.randomUUID(),
                "4", false));
        answers.add(new Answer(UUID.randomUUID(),
                "2", false));
        answers.add(new Answer(UUID.randomUUID(),
                "3", false));
        answers.add(new Answer(UUID.randomUUID(),
                "1", false));
        question.setPossibleAnswers(answers);
        return question;
    }

    public Set<Result> resultsCreation(int count){

        Set<Result> results = new HashSet<>();
        for (int i = 0; i < count; ++i){
            results.add(new Result(UUID.randomUUID(),15 * new Random().nextDouble()));
        }
        return results;
    }
}