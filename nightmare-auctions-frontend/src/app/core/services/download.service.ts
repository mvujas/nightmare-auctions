import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  constructor(private http: HttpClient) { }

  public downloadPDF(link: string, filename: string, errorHandler: (error: any) => void) {
    this.getPDF(link).subscribe((data: any) => {
      let blob = new Blob([data], {type: 'application/pdf'});

      let downloadURL = window.URL.createObjectURL(data);
      let link = document.createElement('a');
      link.href = downloadURL;
      link.download = filename;
      link.click();
    
    },
    errorHandler);
  }

  private getPDF(link: string) {
    const httpOptions = {
      responseType: 'blob' as 'json'
    };
    return this.http.get(link, httpOptions);
  }

}
