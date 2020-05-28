import { Component, OnInit } from '@angular/core';
import { User } from '../../Model/User.model';
import { CapstoreService } from '../../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [],
})
export class HomeComponent implements OnInit {
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router
  ) {}

  ngOnInit(): void {}
}