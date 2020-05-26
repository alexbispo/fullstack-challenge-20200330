import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root'
})
export class ProductsUploadService {

  constructor(private http: HttpClient) {}

  upload(file: File) {
    const fomrData = new FormData();
    fomrData.append('file', file);

    return this.http.post(`${environment.apiUrl}/products`,
     fomrData,
     {
        observe: 'events',
        reportProgress: true
     }
    );
  }
}
