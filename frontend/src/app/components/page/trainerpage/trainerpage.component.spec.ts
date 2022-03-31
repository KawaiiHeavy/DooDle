import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerpageComponent } from './trainerpage.component';

describe('TrainerpageComponent', () => {
  let component: TrainerpageComponent;
  let fixture: ComponentFixture<TrainerpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainerpageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
