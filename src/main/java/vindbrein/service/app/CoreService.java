package vindbrein.service.app;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;


public interface CoreService {
	
	public void visitarOfertaLaboral(OfertaLaboral ofertaLaboral, Postulante postulante);
	
	public void postularOfertaLaboral(OfertaLaboral ofertaLaboral, Postulante postulante);

}
