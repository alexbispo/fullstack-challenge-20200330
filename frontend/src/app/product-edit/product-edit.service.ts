import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  environment } from 'src/environments/environment';
import { ProductEdit } from './product-edit';

@Injectable({
  providedIn: 'root'
})
export class ProductEditService {

  constructor(private http: HttpClient) {}

  execute(productId: string, product: ProductEdit) {
    return this.http.put(`${environment.apiUrl}/products/${productId}`, product);
  }
}
