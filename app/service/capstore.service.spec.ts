import { TestBed, inject } from '@angular/core/testing';

import { CapstoreService } from './capstore.service';

describe('CapstoreService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CapstoreService]
    });
  });

  it('should be created', inject([CapstoreService], (service: CapstoreService) => {
    expect(service).toBeTruthy();
  }));
});
