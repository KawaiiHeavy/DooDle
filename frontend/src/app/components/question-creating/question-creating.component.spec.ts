import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionCreatingComponent } from './question-creating.component';

describe('QuestionCreatingComponent', () => {
  let component: QuestionCreatingComponent;
  let fixture: ComponentFixture<QuestionCreatingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionCreatingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionCreatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
