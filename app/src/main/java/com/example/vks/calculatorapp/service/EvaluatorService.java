package com.example.vks.calculatorapp.service;

import com.example.vks.calculatorapp.service.servicemodel.ComputeRequest;
import com.example.vks.calculatorapp.service.servicemodel.ComputeResponse;

/**
 * Interface:   Service for evaluating expressions.
 *
 * Created by Umair Khan on 9/7/2017.
 */
public interface EvaluatorService {

    /**
     * Method:  Computes an specified expression in the request.
     *
     * @param request
     * @return
     */
    ComputeResponse compute(ComputeRequest request);
}
