package vindbrein.manager.postulante;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import vindbrein.service.PostulanteService;

@Controller
@Scope("Session")
public class PostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(PostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("postulanteService")
	private PostulanteService postulanteService;
	
	
	
}
