import { Component, OnInit } from '@angular/core';
import {faPersonBooth} from '@fortawesome/free-solid-svg-icons'
import {faList} from '@fortawesome/free-solid-svg-icons'
import {faPiggyBank} from '@fortawesome/free-solid-svg-icons'
import {faPenNib} from '@fortawesome/free-solid-svg-icons'
import {faFolderOpen} from '@fortawesome/free-solid-svg-icons'
import {faCalculator} from '@fortawesome/free-solid-svg-icons'
import {faFileInvoice} from '@fortawesome/free-solid-svg-icons'
import {faUsers} from '@fortawesome/free-solid-svg-icons'
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  faPersonBooth = faPersonBooth;
  faList=faList
  faPiggyBank=faPiggyBank
  faPenNib=faPenNib
  faFolderOpen=faFolderOpen
  faCalculator=faCalculator
  faFileInvoice=faFileInvoice
  faUsers=faUsers
  constructor() { }

  ngOnInit(): void {
  }

}
