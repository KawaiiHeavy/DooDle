import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Test } from 'src/app/models/test.model';
import { TestService } from 'src/app/services/test.service';

export interface ResultData {
  id: string;
  nickname: string;
  score: number;
}

@Component({
  selector: 'app-test-detail-info',
  templateUrl: './test-detail-info.component.html',
  styleUrls: ['./test-detail-info.component.scss']
})
export class TestDetailInfoComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nickname', 'score'];
  dataSource: MatTableDataSource<ResultData>

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  test: Test;

  constructor(private router: Router, 
              private testService: TestService) 
  {
    this.test = history.state;
    console.log(this.router.getCurrentNavigation().extras.state);
    const results = [];
    this.test.results.forEach(result => {
      results.push(this.createMemberData(result.id, result.participant.nickname, result.score))
    })
    this.dataSource = new MatTableDataSource(results);
  }
    

  ngOnInit(): void {
    
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  createMemberData(userId: string, userNickname: string, userScore: number){
    return {
      id: userId,
      nickname: userNickname,
      score: userScore
    }
  }
}
