package com.example.vaadincomboboxbreaker;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("Breaking Vaadin ComboBox with: " + 
        		com.vaadin.shared.Version.getFullVersion()));
        Label issueLabel = new Label("Demonstrate ComboBox problem at <a href=\"https://github.com/vaadin/framework/issues/10549\">Vaadin ComboBox broken with setPageLength=0 and suggestions after entering value</a><br>"
        		+ "Type any character into the ComboBox and quickly press [Enter] before the ComboBox loads the value list of available values");
        issueLabel.setContentMode(ContentMode.HTML);
        layout.addComponent(issueLabel);
        
        String comboBoxValuesString = "First Entry,Second Entry,Third entry, Fourth entry";
        List<String> comboBoxValues = Arrays.asList((comboBoxValuesString).split(","));
        // the combobox can only be broken if any value is entered before the list of suggestions is loaded
        
	    // Create new ComboBox
        ComboBox<String> demobox = new ComboBox<>();
        demobox.setPageLength(0); // <-- this option is required to break the ComboBox
        demobox.setItems(comboBoxValues);
//        demobox.setTextInputAllowed(true);
//        demobox.setWidth("600px");
        demobox.setId("testbox");
 
        layout.addComponent(demobox);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
    }
    
}
