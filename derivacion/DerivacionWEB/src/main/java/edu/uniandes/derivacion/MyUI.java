package edu.uniandes.derivacion;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import edu.uniandes.derivacion.enums.Caracteristicas;

/**
 * Implementa la pagina de configuracion del producto
 * 
 * @author juan camilo cerquera lozada<jc.cerquera10@uniandes.edu.co>
 *
 */
@Theme("mytheme")
@Widgetset("edu.uniandes.derivacion.MyAppWidgetset")
public class MyUI extends UI {

	private static final long serialVersionUID = -2494457110738218967L;

	@SuppressWarnings("serial")
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Panel panel = new Panel("Configuración del Producto");
//		panel.addStyleName("mypanelexample");
		panel.setSizeUndefined(); // Shrink to fit content
		layout.addComponent(panel);

		// Create the content
		VerticalLayout verticalLayout = new VerticalLayout();

		Caracteristica redesSociales = new Caracteristica();
		redesSociales.setNombre("Redes Sociales");
		redesSociales.setCodigo(Caracteristicas.REDES_SOCIALES.toString());
		redesSociales.setSeleccionada(false);
		final BeanFieldGroup<Caracteristica> binder1 = new BeanFieldGroup<Caracteristica>(
				Caracteristica.class);
		binder1.setItemDataSource(redesSociales);

		Caracteristica twitter = new Caracteristica();
		twitter.setNombre("Twitter");
		twitter.setCodigo(Caracteristicas.TWITTER.toString());
		twitter.setSeleccionada(false);
		final BeanFieldGroup<Caracteristica> binder2 = new BeanFieldGroup<Caracteristica>(
				Caracteristica.class);
		binder2.setItemDataSource(twitter);

		Caracteristica facebook = new Caracteristica();
		facebook.setNombre("Facebook");
		facebook.setCodigo(Caracteristicas.FACEBOOK.toString());
		facebook.setSeleccionada(false);
		final BeanFieldGroup<Caracteristica> binder3 = new BeanFieldGroup<Caracteristica>(
				Caracteristica.class);
		binder3.setItemDataSource(facebook);

		final TreeTable ttable = new TreeTable("");
		ttable.addContainerProperty("", CheckBox.class, "");
		ttable.setWidth("20em");

		// Create the tree nodes
		final CheckBox checkRedes = new CheckBox(redesSociales.getNombre());
		checkRedes.setImmediate(true);
		binder1.bind(checkRedes, "seleccionada");
		ttable.addItem(new Object[] { checkRedes }, 0);

		final CheckBox checkFacebook = new CheckBox(facebook.getNombre());
		checkFacebook.setImmediate(true);
		binder2.bind(checkFacebook, "seleccionada");
		ttable.addItem(new Object[] { checkFacebook }, 1);
		checkFacebook.setEnabled(false);


		final CheckBox checkTwitter = new CheckBox(twitter.getNombre());
		checkTwitter.setImmediate(true);
		binder3.bind(checkTwitter, "seleccionada");
		ttable.addItem(new Object[] { checkTwitter }, 2);
		checkTwitter.setEnabled(false);
		
		checkRedes.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (checkRedes.getValue() == true) {
					checkFacebook.setEnabled(true);
					checkTwitter.setEnabled(true);
				}else {
					checkFacebook.setEnabled(false);
					checkTwitter.setEnabled(false);
				}
			}
		});
		
		checkFacebook.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (checkFacebook.getValue() == true) {
					if (checkTwitter.getValue() == true) {
						checkTwitter.setValue(false);
					}
				}
			}
		});
		
		checkTwitter.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (checkTwitter.getValue() == true) {
					if (checkFacebook.getValue() == true) {
						checkFacebook.setValue(false);
					}
				}
			}
		});

		// Set the hierarchy
		ttable.setParent(1, 0);
		ttable.setParent(2, 0);

//		// Expand the tree
		ttable.setCollapsed(2, false);
		for (Object itemId : ttable.getItemIds())
			ttable.setCollapsed(itemId, false);

		ttable.setPageLength(ttable.size());

		verticalLayout.addComponent(ttable);

		Button boton = new Button("Configurar");
		boton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder1.commit();
					binder2.commit();
					binder3.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				}

				String featuresSeleccionadas = "";
				if (binder1.getItemDataSource().getBean().getSeleccionada()) {
					featuresSeleccionadas += binder1.getItemDataSource()
							.getBean().getCodigo();
				}
				if (binder2.getItemDataSource().getBean().getSeleccionada()) {
					featuresSeleccionadas += binder2.getItemDataSource()
							.getBean().getCodigo();
				}
				if (binder3.getItemDataSource().getBean().getSeleccionada()) {
					featuresSeleccionadas += binder3.getItemDataSource()
							.getBean().getCodigo();
				}

				// llamado al servicio web de configuración de producto.
				// ResteasyClient client = new ResteasyClientBuilder().build();
				// ResteasyWebTarget target =
				// client.target("http://localhost:8080/estampateWEB/webresources/featuresApp/setFeatures");
				// target.queryParam("features", featuresSeleccionadas);
				// target.request(MediaType.APPLICATION_JSON);
				try {
					System.out.println("ANTES DE EJECUTAR EL SERVICIO");
					ClientRequest request = new ClientRequest(
							"http://localhost:8080/estampateWEB/webresources/featuresApp/setFeatures");
					request.accept(MediaType.APPLICATION_JSON);
					request.body(MediaType.APPLICATION_JSON,featuresSeleccionadas);

					ClientResponse<String> response  = request.post(String.class);
					System.out.println("CODIGO_RESPONSE: "+response.getStatus());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		verticalLayout.addComponent(boton);
		verticalLayout.setComponentAlignment(boton, Alignment.MIDDLE_CENTER);
		verticalLayout.setSpacing(true);

		panel.setContent(verticalLayout);
		layout.addComponent(panel);
		layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

	}

	@SuppressWarnings("serial")
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
