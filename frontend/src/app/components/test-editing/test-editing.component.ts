import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Test } from 'src/app/models/test.model';
import { TestService } from 'src/app/services/test.service';

@Component({
  selector: 'app-test-editing',
  templateUrl: './test-editing.component.html',
  styleUrls: ['./test-editing.component.scss']
})
export class TestEditingComponent implements OnInit {

  test: Test;

  constructor(private router: Router, 
    private testService: TestService) { 
      this.test = history.state;
      console.log(this.router.getCurrentNavigation().extras.state);
    }

  ngOnInit(): void {
    
  }

}
