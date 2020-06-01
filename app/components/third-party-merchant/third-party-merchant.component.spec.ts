import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThirdPartyMerchantComponent } from './third-party-merchant.component';

describe('ThirdPartyMerchantComponent', () => {
  let component: ThirdPartyMerchantComponent;
  let fixture: ComponentFixture<ThirdPartyMerchantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThirdPartyMerchantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThirdPartyMerchantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
