import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultDetailInfoComponent } from './result-detail-info.component';

describe('ResultDetailInfoComponent', () => {
  let component: ResultDetailInfoComponent;
  let fixture: ComponentFixture<ResultDetailInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultDetailInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultDetailInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
