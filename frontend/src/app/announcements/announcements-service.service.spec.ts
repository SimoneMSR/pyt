/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AnnouncementsServiceService } from './announcements-service.service';

describe('Service: AnnouncementsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnnouncementsServiceService]
    });
  });

  it('should ...', inject([AnnouncementsServiceService], (service: AnnouncementsService) => {
    expect(service).toBeTruthy();
  }));
});
