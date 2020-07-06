import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VillesadminComponent } from './villesadmin.component';

describe('VillesadminComponent', () => {
  let component: VillesadminComponent;
  let fixture: ComponentFixture<VillesadminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VillesadminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VillesadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
