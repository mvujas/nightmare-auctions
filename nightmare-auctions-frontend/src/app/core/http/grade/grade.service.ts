import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GradeHolder } from '@app/shared/model/grade-holder';

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  constructor(private http: HttpClient) { }

  getWaitingGrades(username: string): Observable<GradeHolder[]> {
    return this.http.get<GradeHolder[]>(`api/user/${username}/grades/waiting`);
  }

  saveGradeValue(id: number, value: number) {
    return this.http.post(`api/grade/${id}`, { value: value });
  }

}
