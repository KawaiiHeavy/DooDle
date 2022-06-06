import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerCreatingComponent } from './answer-creating.component';

describe('AnswerCreatingComponent', () => {
  let component: AnswerCreatingComponent;
  let fixture: ComponentFixture<AnswerCreatingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerCreatingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerCreatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
