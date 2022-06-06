import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trainerpage',
  templateUrl: './trainerpage.component.html',
  styleUrls: ['./trainerpage.component.scss']
})
export class TrainerpageComponent implements OnInit {

  constructor() { }

  creatingTestShow : boolean = false;

  ngOnInit(): void {
  }

  changeTestCreatingVisibility(){
    this.creatingTestShow = !this.creatingTestShow;
  }
}
