package com.intuit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @apiNote This controller will receive the requests for for following types:
 *  1. New Label
 *  2. Update Label
 *  3. Delete Label
 */
@Controller
@RequestMapping(value = "/v1/notes")
public class LabelController {
}
