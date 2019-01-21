package gov.healthit.chpl.aqa.model;

/**
 * Class ApiImplementationNote model.
 */
public class ApiImplementationNote {
    private String implementationNote;
    private String controllerName;
    private String apiEndPoint;
    private String endPointLink;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(final String controllerName) {
        this.controllerName = controllerName;
    }

    public String getApiEndPoint() {
        return apiEndPoint;
    }

    public void setApiEndPoint(final String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    public String getEndPointLink() {
        return endPointLink;
    }

    public void setEndPointLink(final String endPointLink) {
        this.endPointLink = endPointLink;
    }

    public String getImplementationNote() {
        return implementationNote;
    }

    public void setImplementationNote(final String implementationNote) {
        this.implementationNote = implementationNote;
    }
}
