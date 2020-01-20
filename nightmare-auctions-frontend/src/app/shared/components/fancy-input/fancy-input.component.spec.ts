/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FancyInputComponent } from './fancy-input.component';

describe('FancyInputComponent', () => {
  let component: FancyInputComponent;
  let fixture: ComponentFixture<FancyInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FancyInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FancyInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
