import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyMerchantComponent } from './verify-merchant.component';

describe('VerifyMerchantComponent', () => {
  let component: VerifyMerchantComponent;
  let fixture: ComponentFixture<VerifyMerchantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifyMerchantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyMerchantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
