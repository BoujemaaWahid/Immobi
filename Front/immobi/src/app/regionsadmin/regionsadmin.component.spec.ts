import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionsadminComponent } from './regionsadmin.component';

describe('RegionsadminComponent', () => {
  let component: RegionsadminComponent;
  let fixture: ComponentFixture<RegionsadminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionsadminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionsadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
