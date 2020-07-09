import { TestBed } from '@angular/core/testing';

import { SocketmsgService } from './socketmsg.service';

describe('SocketmsgService', () => {
  let service: SocketmsgService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SocketmsgService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
