import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestDetailInfoComponent } from './test-detail-info.component';

describe('TestDetailInfoComponent', () => {
  let component: TestDetailInfoComponent;
  let fixture: ComponentFixture<TestDetailInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestDetailInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestDetailInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
