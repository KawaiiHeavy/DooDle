import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Result } from 'src/app/models/result.model';
import { Test } from 'src/app/models/test.model';
import { ResultService } from 'src/app/services/result.service';
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
  dataSource: MatTableDataSource<Result>

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  test: Test;
  results: Result[];

  constructor(private router: Router, 
              private testService: TestService,
              private resultService: ResultService) 
  {
    this.test = history.state;
    resultService.findResultsByTest(this.test.id).subscribe(results => this.results = results);
    this.dataSource = new MatTableDataSource(this.results);
  }
    

  ngOnInit(): void {
    
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    console.log("Sorting: " + this.sort);
    console.log("Pagination: " + this.paginator);
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
