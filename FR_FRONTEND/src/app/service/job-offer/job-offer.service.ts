import { Injectable  } from '@angular/core';
import { environment } from "../../../environments/environment";
import {Observable, Observer, Subscriber, Subscription, TeardownLogic, throwIfEmpty, Unsubscribable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  JobOfferApplicationsPageResponse,
  JobOfferChangeVisibilityRequest,
  JobOfferPageResponse,
  JobOfferRequest,
  JobOfferResponse
} from "../../interfaces/jobOffer.model";

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  private baseUrl: string = environment.backendHost+"/job_offers";



  constructor(private http:HttpClient) {
  }

  getAllJobOffers(queryParams:Map<string,string>):Observable<JobOfferPageResponse>{
    let paramsHttp = new HttpParams();
    queryParams.forEach((value, key, map)=>{
      paramsHttp = paramsHttp.append(key,value)
    })
    console.log(queryParams)
    return this.http.get(this.baseUrl,{
      params : paramsHttp
    }) as  Observable<any>
  }


  addNewOffer(jobOfferRequest:JobOfferRequest):Observable<JobOfferResponse>{
    return this.http.post(this.baseUrl,jobOfferRequest) as Observable<JobOfferResponse>
  }

  //change_visibility // only admin or agent will have access to this function
  changeJobOfferVisibility(jobOfferChangeVisibilityRequest:JobOfferChangeVisibilityRequest):Observable<JobOfferResponse>{
    return this.http.post(this.baseUrl+"/change_visibility",
      jobOfferChangeVisibilityRequest) as Observable<JobOfferResponse>
  }


  getOneJobOffer(id: string):Observable<JobOfferResponse> {
      return this.http.get(this.baseUrl+`/${id}`) as  Observable<JobOfferResponse>
  }

  getAllJobOfferByCompanyId(id: number) :Observable<JobOfferPageResponse>{
      return this.http.get(this.baseUrl+`/company/${id}`) as  Observable<JobOfferPageResponse>
  }

  getJobOfferApplicationByJobOfferIdAndCompanyId(id: number, jobOfferId: number) :Observable<Array<JobOfferApplicationsPageResponse>>{
    return this.http.get(this.baseUrl+`/company/${id}/applications/${jobOfferId}`) as  Observable<Array<JobOfferApplicationsPageResponse>>
  }

  createJobOffer(value: any) {
    return this.http.post(this.baseUrl,value) as Observable<JobOfferResponse>
  }
}


export class NumberSubscriber extends Subscriber<number> {


  override next(value?: number) {
    super.next(value);
  }

  override complete() {
    super.complete();
  }

  override error(err?: any) {
    super.error(err);
  }
}
// Path: src/app/features/job-offer-detail/job-offer-detail.component.html
