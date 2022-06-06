import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestCreatingComponent } from './test-creating.component';

describe('TestCreatingComponent', () => {
  let component: TestCreatingComponent;
  let fixture: ComponentFixture<TestCreatingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestCreatingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestCreatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
