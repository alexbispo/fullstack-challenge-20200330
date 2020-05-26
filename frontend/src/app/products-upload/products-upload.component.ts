import { OnInit, Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProductsUploadService } from './products-upload.service';
import { HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  templateUrl: './products-upload.component.html',
  selector: 'fsc-products-upload',
  styleUrls: ['./products-upload.component.css']
})
export class ProductsUploadComponent implements OnInit {

  productsForm: FormGroup;
  file: File;
  percentDone = 0;
  @Output() onUploadCompleted = new EventEmitter<boolean>()
  isValidFile = false;
  showFileError = false;

  constructor(
    private formBuilder: FormBuilder,
    private productsUploadService: ProductsUploadService
  ) { }

  ngOnInit(): void {
    this.productsForm = this.formBuilder.group({
      file: ['', Validators.required]
    });
  }

  upload() {
    if (!this.isValidFile) {
      alert('O formato do arquivo é inválido. Anexar arquivo .json');
    }
    this.productsUploadService.upload(this.file)
      .subscribe((event: HttpEvent<any>) => {
        if (event.type == HttpEventType.UploadProgress) {
          this.percentDone = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          alert('Arquivo enviado com sucesso!');
          this.percentDone = 0;
          this.isValidFile = false;
          this.productsForm.reset();
          this.onUploadCompleted.emit(event.ok);
        }
      }, err => {
        console.error(err);
        alert('Erro ao tentar enviar o arquivo para o servidor. Por favor tente novamente mais tarde.');
        this.onUploadCompleted.emit(false);
      });
  }

  handleFile(file: File) {
    this.file = file;
    const fileExtension = this.file.name.split('.').pop();

    if (fileExtension === 'json') {
      this.isValidFile = true;
    } else {
      this.isValidFile = false;
      this.showFileError = true;
    }
  }
}
