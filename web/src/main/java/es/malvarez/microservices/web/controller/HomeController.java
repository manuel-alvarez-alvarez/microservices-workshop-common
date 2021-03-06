package es.malvarez.microservices.web.controller;

import es.malvarez.microservices.web.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for our home sweet home
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final String home;

    @Autowired
    protected HomeController(
            @Value("${api.href}") final String apiHref,
            @Value("${api.mode:collisions}") final String apiMode,
            @Value("classpath:/static/index.html") final Resource index) {
        this.home = IOUtils.read(index).replace("{{api}}", apiHref).replace("{{mode}}", apiMode);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String home() {
        return this.home;
    }


}
