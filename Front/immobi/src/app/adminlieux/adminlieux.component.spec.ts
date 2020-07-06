import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminlieuxComponent } from './adminlieux.component';

describe('AdminlieuxComponent', () => {
  let component: AdminlieuxComponent;
  let fixture: ComponentFixture<AdminlieuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminlieuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminlieuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
