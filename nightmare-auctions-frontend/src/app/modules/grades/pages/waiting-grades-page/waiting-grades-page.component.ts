import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/core/authentication/authentication.service';
import { UserAuthHolder } from '@app/shared/domain/user-auth-holder';
import { error } from 'protractor';
import { GradeHolder } from '@app/shared/model/grade-holder';
import { GradeService } from '@app/core/http/grade/grade.service';

@Component({
  selector: 'app-waiting-grades-page',
  templateUrl: './waiting-grades-page.component.html',
  styleUrls: ['./waiting-grades-page.component.scss']
})
export class WaitingGradesPageComponent implements OnInit {

  constructor(private authService: AuthenticationService,
    private gradeService: GradeService) { }

  errorLoadingUser = false;
  userDetails: UserAuthHolder = null;

  errorLoadingWaitingGrades = false;
  waitingGrades: GradeHolder[] = null;

  ngOnInit() {
    this.authService.basicUserDetails.subscribe(this.handleUserChange.bind(this));
  }

  handleUserChange(newUserDetails) {
    this.userDetails = newUserDetails;
    this.errorLoadingUser = newUserDetails === null;
    if(!this.errorLoadingUser) {
      this.loadGrades(this.userDetails.username);
    }
  }

  loadGrades(username: string) {
    this.gradeService.getWaitingGrades(username).subscribe(
      this.successfulLoadingOfGrades.bind(this),
      this.errorLoadingGrades.bind(this)
    );
  }

  successfulLoadingOfGrades(grades) {
    this.errorLoadingWaitingGrades = false;
    this.waitingGrades = grades;
  }

  errorLoadingGrades(response) {
    this.errorLoadingWaitingGrades = true;
  }

  refreshGrades() {
    this.loadGrades(this.userDetails.username);
  }

}
