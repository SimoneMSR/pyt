/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AnnouncementsService } from './announcements.service';

describe('Service: AnnouncementsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnnouncementsService]
    });
  });

  it('should ...', inject([AnnouncementsService], (service: AnnouncementsService) => {
    expect(service).toBeTruthy();
  }));
});
