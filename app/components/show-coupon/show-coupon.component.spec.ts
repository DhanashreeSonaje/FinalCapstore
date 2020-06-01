import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCouponComponent } from './show-coupon.component';

describe('ShowCouponComponent', () => {
  let component: ShowCouponComponent;
  let fixture: ComponentFixture<ShowCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
