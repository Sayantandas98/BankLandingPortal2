import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  dataEmitter=new EventEmitter<boolean>();
  constructor() { }
  
  raiseDataEmitter(data:boolean){
    this.dataEmitter.emit(data);
  }
}
