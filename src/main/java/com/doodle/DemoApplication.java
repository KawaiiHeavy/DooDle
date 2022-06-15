package com.doodle;

import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.QuestionService;
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
    private QuestionService questionService;

    @Autowired
    TestService testService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Creating roles
        roleCreation();

        getTest1();
        getTest2();
        getTest3();

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

        User leader = (User)members.toArray()[0];

        System.out.println(leader);

    }

//    public Set<Question> questionCreation(int questionCount, int answerCount){
//        String prefix = "question";
//
//        Set<Question> questions = new HashSet<>();
//        Set<Answer> answers = answerCreation(answerCount);
//
//        for (int i = 0; i < questionCount; ++i){
//            UUID questionId = UUID.randomUUID();
//            Question question = new Question(
//                    questionId,
//                    new StringBuffer(prefix).append(i).toString(),
//                    15 * new Random().nextDouble());
//            for (Answer answer: answers){
//                question.addAnswer(answer);
//            }
//            questions.add(question);
//        }
//        return questions;
//    }

//    public Set<Answer> answerCreation(int count){
////        String prefix = "answer";
////        Set<Answer> answers = new HashSet<>();
////        for (int i = 0; i < count; ++i){
////            question.addAnswer(new Answer(
////                    UUID.randomUUID(),
////                    new StringBuffer(prefix).append(i).toString(),
////                    new Random().nextBoolean()
////            ));
////        }
////        return answers;
//    }

    public Question getQuestion1(){
        Question question = new Question();
        question.setQuestionText("Для каких объектов маршрутизатор выбирает маршрут?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для пакетов третьего уровня", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для фреймов второго уровня", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для дейтаграмм второго уровня", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для битов первого уровня", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для сегментов четвертого уровня", false));

        questionService.save(question);

        return question;
    }

    public Question getQuestion2(){
        Question question = new Question();
        question.setQuestionText("Какие устройства сети получают ARP запрос (ARP Request)?");
        question.setScoreWeight(15.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "только компьютеры той локальной сети, которой принадлежит хост, пославший ARP запрос", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "только порты коммутаторов той локальной сети, которой принадлежит хост, пославший ARP запрос", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос, и порты маршрутизаторов соседних сетей", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion3(){
        Question question = new Question();
        question.setQuestionText("Какое значение параметра Cipher Type в настройках точки доступа D-Link DWL-2100AP использовалось при выполнении лабораторной работы?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "AES", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "TKIP", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "AUTO", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "CCMP", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion4(){
        Question question = new Question();
        question.setQuestionText("Точка доступа установлена на потолке Вашего офиса и подключена. Какой параметр должен быть (как минимум) настроен на точке доступа, чтобы обеспечить взаимодействие с ней беспроводных клиентов?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "SSID", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "WEP", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "AES", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "TKIP", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "PSK", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion5(){
        Question question = new Question();
        question.setQuestionText("При выборе параметра (channel - канал) при настройке беспроводной точки доступа стандарта IEEE 802.11g следует задавать номер канала так, чтобы он отличался от номеров каналов соседних точек доступа с мощным сигнала не менее чем на:");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "4", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "1", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "2", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "3", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "8", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion6(){
        Question question = new Question();
        question.setQuestionText("Одна точка доступа 802.11g была настроена и установлена в центре квадратного офиса. Некоторые пользователи испытывают замедление в связи и потерю пакетов, в то время, как большинство пользователей работают с сетью в полную силу. В чем может быть проблема?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Помехи от беспроводных телефонов", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Мешают металлические шкафы", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Неподходящие антенны или неверное их направление/расположение", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Не настроен SSID", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Неверно настроен SSID", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion7(){
        Question question = new Question();
        question.setQuestionText("В чем состоит преимущество дистанционно-векторных алгоритмов маршрутизации (DVA)?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Эти протоколы просты в вычислительном отношении", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Эти протоколы легко реализуются в крупных сетях", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Для них маловероятно бесконечное накапливание количества переходов(зацикливание в кольцевом маршруте)", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Хорошо работают в сетях любых размеров", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion8(){
        Question question = new Question();
        question.setQuestionText("Где предпочтительнее всего размещать расширенные списки управления доступом (ACL)?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Как можно ближе к отправителям трафика", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "В сети Internet", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Ни в одном из перечисленных выше мест", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "На магистральных каналах сети", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion9(){
        Question question = new Question();
        question.setQuestionText("В чем цель использования команды traceroute?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());

        question.addAnswer(new Answer(UUID.randomUUID(),
                "Проверить, какой маршрут пройдет пакет по пути к точке назначения", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Создать карту устройств в сети", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Показать текущие настройки TCP/IP", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Проверить какой MAC-адрес устройства соответсвует его IP-адресу", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Показать значение MTU для каждого маршрутизатора в заданном маршруте до точки назначения", false));

        questionService.save(question);
        return question;
    }

    public Question getQuestion10(){
        Question question = new Question();
        question.setQuestionText("Сколько неперекрывающихся каналов можно организовать в диапазоне 2,4 ГГц при использовании стандарта IEEE 802.11a?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Ни одного", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "4", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "2", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "3", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "1", false));

        questionService.save(question);
        return question;
    }

    // Лилечка
    public Question getQuestion11(){
        Question question = new Question();
        question.setQuestionText("Кто это?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Алла Пугачева", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Владимир Путин", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Лилия Владимировна", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Людмила Владимировна", false));
        question.setImageUrl("http://tk.ssau.ru/images/img/staff/academic/Loganova-300-!!.JPG");

        questionService.save(question);
        return question;
    }

    // Головастиков
    public Question getQuestion12(){
        Question question = new Question();
        question.setQuestionText("Кто это?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Куприянов Александр Викторович", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Коломиец Эдуард Иванович", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Головастиков Никита Сергеевич", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Суханов Сергей Васильевич", false));
        question.setImageUrl("https://cabinet.ssau.ru/storage/photos/photo-284846061-619dfedfb2a0b.jpg");

        questionService.save(question);
        return question;
    }

    // ОВГ
    public Question getQuestion13(){
        Question question = new Question();
        question.setQuestionText("Кто это?");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Горячкин Олег Валерьевич", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Калядин Владимир Петрович", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "ОВГ", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "КВП", false));

        question.setImageUrl("https://www.psuti.ru/sites/default/files/field/image/goryachkin_o.v-1.jpg");
        questionService.save(question);
        return question;
    }

    // Храмов
    public Question getQuestion14(){
        Question question = new Question();
        question.setQuestionText("Кто это");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Филонин Олег Васильевич", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Храмов Александр Григорьевич", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Кузьмин Бяка Бякович", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Микенберг Михаил Аврамович", false));
        question.setImageUrl("http://tk.ssau.ru/images/img/staff/academic/khramov.jpg");


        questionService.save(question);
        return question;
    }

    // Гайдель
    public Question getQuestion15(){
        Question question = new Question();
        question.setQuestionText("Кто это");
        question.setScoreWeight(10.0);
        question.setId(UUID.randomUUID());
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Шлакоблок", true));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Кириленко Михаил Сергеевич (КМС)", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Дегтярев Сергей Александрович", false));
        question.addAnswer(new Answer(UUID.randomUUID(),
                "Гайдель Андрей Викторович", true));
        question.setImageUrl("https://do.ssau.ru/img/imageTeacher/6826фотоГайдельА.В..png");

        questionService.save(question);
        return question;
    }

    public Test getTest1(){

        Set<User> members = userCreation(10);
        User leader = (User)members.toArray()[0];

        double maxBall = 0;

        maxBall += getQuestion1().getScoreWeight();
        maxBall += getQuestion2().getScoreWeight();
        maxBall += getQuestion3().getScoreWeight();
        maxBall += getQuestion4().getScoreWeight();
        maxBall += getQuestion5().getScoreWeight();

        UUID id = UUID.randomUUID();

        Test test = new Test();
        test.setId(id);
        test.setTitle("test1");
        test.setMaxBall(maxBall);
        test.setSeconds(300);
        test.setMembers(members);
        test.setCreator(leader);

        test.addQuestion(getQuestion1());
        test.addQuestion(getQuestion2());
        test.addQuestion(getQuestion3());
        test.addQuestion(getQuestion4());
        test.addQuestion(getQuestion5());

        test.setResults(resultsCreation(10, test, leader));

        testService.saveTest(test);

        return test;
    }

    public Test getTest2(){

        Set<User> members = userCreation(10);
        User leader = (User)members.toArray()[0];

        double maxBall = 0;

        maxBall += getQuestion6().getScoreWeight();
        maxBall += getQuestion7().getScoreWeight();
        maxBall += getQuestion8().getScoreWeight();
        maxBall += getQuestion9().getScoreWeight();
        maxBall += getQuestion10().getScoreWeight();

        UUID id = UUID.randomUUID();

        Test test = new Test();
        test.setTitle("test2");
        test.setId(id);
        test.setMaxBall(maxBall);
        test.setSeconds(300);
        test.setMembers(members);
        test.setCreator(leader);

        test.addQuestion(getQuestion6());
        test.addQuestion(getQuestion7());
        test.addQuestion(getQuestion8());
        test.addQuestion(getQuestion9());
        test.addQuestion(getQuestion10());

        test.setResults(resultsCreation(10, test, leader));

        testService.saveTest(test);

        return test;
    }

    public Test getTest3(){

        Set<User> members = userCreation(10);
        User leader = (User)members.toArray()[0];

        double maxBall = 0;

        maxBall += getQuestion11().getScoreWeight();
        maxBall += getQuestion12().getScoreWeight();
        maxBall += getQuestion13().getScoreWeight();
        maxBall += getQuestion14().getScoreWeight();
        maxBall += getQuestion15().getScoreWeight();

        UUID id = UUID.randomUUID();

        Test test = new Test();
        test.setTitle("test3");
        test.setId(id);
        test.setMaxBall(maxBall);
        test.setSeconds(300);
        test.setMembers(members);
        test.setCreator(leader);

        test.addQuestion(getQuestion11());
        test.addQuestion(getQuestion12());
        test.addQuestion(getQuestion13());
        test.addQuestion(getQuestion14());
        test.addQuestion(getQuestion15());

        test.setResults(resultsCreation(10, test, leader));

        testService.saveTest(test);

        return test;
    }

    public Set<Result> resultsCreation(int count, Test test, User participant){

        Set<User> members = userCreation(10);

        Set<Result> results = new HashSet<>();
        for (int i = 0; i < count; ++i){
            Result result = new Result(UUID.randomUUID(),15 * new Random().nextDouble());
            result.saveTest(test);
            results.add(result);
            result.setParticipant(participant);
        }
        return results;
    }
}