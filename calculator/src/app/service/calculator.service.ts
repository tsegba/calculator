import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {

  constructor(private httpClient: HttpClient) {
}
  public post<T>(data: any, actionUrl: string): Observable<T> {
    let httpOptions = {
        headers: new HttpHeaders(
            {
                'Content-Type': 'application/json'
            }
        )

    };
    const lData = JSON.stringify(data);
    return this.httpClient.post<T>("localhost:8080/vs/calculator/"+actionUrl, data, httpOptions);
}

}
