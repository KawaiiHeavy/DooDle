import { Injectable } from '@angular/core';
import { timer } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
export class UtilsTimerService  {
  
  timeLeft: number = 60;
  interval;
  subscribeTimer: any;

  constructor() { }

  oberserableTimer() {
    const source = timer(1000, 2000);
    const abc = source.subscribe(val => {
      console.log(val, '-');
      this.subscribeTimer = this.timeLeft - val;
    });
  }

  startTimer() {
    this.interval = setInterval(() => {
      if(this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.pauseTimer();
      }
    },1000)
  }

  pauseTimer() {
    clearInterval(this.interval);
  }

  setTime(seconds: number){
      this.timeLeft = seconds;
  }

  getTime(){
      return this.timeLeft;
  }
}
