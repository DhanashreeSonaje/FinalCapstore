import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendCouponComponent } from './send-coupon.component';

describe('SendCouponComponent', () => {
  let component: SendCouponComponent;
  let fixture: ComponentFixture<SendCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
