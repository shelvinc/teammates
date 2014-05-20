package teammates.test.pageobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import teammates.common.util.Const;
import teammates.common.util.StringHelper;
import teammates.common.util.TimeHelper;

import com.google.appengine.api.datastore.Text;

public class InstructorFeedbackEditPage extends AppPage {
    
    @FindBy(id = "starttime")
    private WebElement startTimeDropdown;
    
    @FindBy(id = "startdate")
    private WebElement startDateBox;
    
    @FindBy(id = "endtime")
    private WebElement endTimeDropdown;
    
    @FindBy(id = "enddate")
    private WebElement endDateBox;
    
    @FindBy (id = "visibletime")
    private WebElement visibleTimeDropDown;
    
    @FindBy (id = "publishtime")
    private WebElement publishTimeDropDown;
    
    @FindBy (id = "timezone")
    private WebElement timezoneDropDown;
    
    @FindBy(id = "graceperiod")
    private WebElement gracePeriodDropdown;
    
    @FindBy(id = "instructions")
    private WebElement instructionsTextBox;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SESSIONVISIBLEBUTTON + "_custom")
    private WebElement customSessionVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_RESULTSVISIBLEBUTTON + "_custom")
    private WebElement customResultsVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SESSIONVISIBLEBUTTON + "_atopen")
    private WebElement defaultSessionVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_RESULTSVISIBLEBUTTON + "_atvisible")
    private WebElement defaultResultsVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_RESULTSVISIBLEBUTTON + "_later")
    private WebElement manualResultsVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SESSIONVISIBLEBUTTON + "_never")
    private WebElement neverSessionVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_RESULTSVISIBLEBUTTON + "_never")
    private WebElement neverResultsVisibleTimeButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SENDREMINDEREMAIL + "_open")
    private WebElement openSessionEmailReminderButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SENDREMINDEREMAIL + "_closing")
    private WebElement closingSessionEmailReminderButton;
    
    @FindBy(id = Const.ParamsNames.FEEDBACK_SESSION_SENDREMINDEREMAIL + "_published")
    private WebElement publishedSessionEmailReminderButton;
    
    @FindBy(id = "fsEditLink")
    private WebElement fsEditLink;    
    
    @FindBy(id = "fsSaveLink")
    private WebElement fsSaveLink;
    
    @FindBy(id = "fsDeleteLink")
    private WebElement fsDeleteLink;
    
    @FindBy(id = "button_openframe")
    private WebElement openNewQuestionButton;

    @FindBy(id = "button_submit_add")
    private WebElement addNewQuestionButton;
    
    @FindBy(id = "questiontext")
    private WebElement questionTextBox;
    
    @FindBy(id = "givertype")
    private WebElement giverDropdown;
    
    @FindBy(id = "recipienttype")
    private WebElement recipientDropdown;
    
    @FindBy(id = "givertype-1")
    private WebElement giverDropdownForQuestion1;
    
    @FindBy(id = "recipienttype-1")
    private WebElement recipientDropdownForQuestion1;
    
    @FindBy(id = "questionedittext-1")
    private WebElement questionEditForQuestion1;
    
    @FindBy(id = "questionsavechangestext-1")
    private WebElement questionSaveForQuestion1;
    
    @FindBy(id = "numofrecipients-")
    private WebElement numberOfRecipients;
    
    @FindBy(xpath = "//input[@name='numofrecipientstype' and @value='max']")
    private WebElement maxNumOfRecipients;
    
    @FindBy(xpath = "//input[@name='numofrecipientstype' and @value='custom']")
    private WebElement customNumOfRecipients;
    
    @FindBy(id = "button_preview_student")
    private WebElement previewAsStudentButton;
    
    @FindBy(id = "button_preview_instructor")
    private WebElement previewAsInstructorButton;
    
    @FindBy(id = "questiongetlink-1")
    private WebElement getLinkButton;
    
    
    public InstructorFeedbackEditPage(Browser browser) {
        super(browser);
    }

    @Override
    protected boolean containsExpectedPageContents() {
        return getPageSource().contains("<h1>Edit Feedback Session</h1>");
    }
    
    public String getCourseId() {
        return browser.driver.findElement(By.name("courseid")).getAttribute("value");
    }
    
    public String getFeedbackSessionName() {
        return browser.driver.findElement(By.name("fsname")).getAttribute("value");
    }
    
    public boolean isCorrectPage (String courseId, String feedbackSessionName) {
        boolean isCorrectCourseId = this.getCourseId().equals(courseId);
        boolean isCorrectFeedbackSessionName = this.getFeedbackSessionName().equals(feedbackSessionName);
        return isCorrectCourseId && isCorrectFeedbackSessionName && containsExpectedPageContents();
    }
    
    public void fillInstructionsBox(String instructions) {
        fillTextBox(instructionsTextBox, instructions);
    }
    
    public void fillQuestionBox(String qnText) {
        fillTextBox(questionTextBox, qnText);
    }
    
    public void fillEditQuestionBox(String qnText, int qnIndex) {
        WebElement questionEditTextBox = browser.driver.findElement(By.id("questiontext-" + qnIndex));
        fillTextBox(questionEditTextBox, qnText);
    }
    
    public void fillNumOfEntitiesToGiveFeedbackToBox(String num) {
        fillTextBox(numberOfRecipients, num);
    }
    
    public void fillMinNumScaleBox(int minScale, int qnNumber) {
        String idSuffix = qnNumber > 0 ? "-" + qnNumber : "";
        WebElement minScaleBox = browser.driver.findElement(By.id("minScaleBox" + idSuffix));
        fillTextBox(minScaleBox, Integer.toString(minScale));
    }
    
    public void fillMaxNumScaleBox(int maxScale, int qnNumber) {
        String idSuffix = qnNumber > 0 ? "-" + qnNumber : "";
        WebElement maxScaleBox = browser.driver.findElement(By.id("maxScaleBox" + idSuffix));
        fillTextBox(maxScaleBox, Integer.toString(maxScale));
    }
    
    public String getMaxNumScaleBox(int qnNumber) {
        String idSuffix = qnNumber > 0 ? "-" + qnNumber : "";
        WebElement maxScaleBox = browser.driver.findElement(By.id("maxScaleBox" + idSuffix));
        return maxScaleBox.getAttribute("value");
    }
    
    public void fillStepNumScaleBox(double step, int qnNumber) {
        String idSuffix = qnNumber > 0 ? "-" + qnNumber : "";
        WebElement stepBox = browser.driver.findElement(By.id("stepBox" + idSuffix));
        fillTextBox(stepBox, StringHelper.toDecimalFormatString(step));
    }
    
    public String getNumScalePossibleValuesString(int qnNumber) {
        String idSuffix = qnNumber > 0 ? "-" + qnNumber : "";
        WebElement possibleValuesSpan = browser.driver.findElement(By.id("numScalePossibleValues" + idSuffix));
        return possibleValuesSpan.getText();
    }
    
    public void clickQuestionEditForQuestion1() {
        questionEditForQuestion1.click();
    }
    
    public void clickMaxNumberOfRecipientsButton() {
        maxNumOfRecipients.click();
    }
    
    public void clickCustomNumberOfRecipientsButton() {
        customNumOfRecipients.click();
    }
    
    public void clickCustomVisibleTimeButton(){
        customSessionVisibleTimeButton.click();
    }

    public void clickCustomPublishTimeButton(){
        customResultsVisibleTimeButton.click();
    }
    
    public void clickDefaultVisibleTimeButton(){
        defaultSessionVisibleTimeButton.click();
    }
    
    public void clickDefaultPublishTimeButton(){
        defaultResultsVisibleTimeButton.click();
    }
    
    public void clickManualPublishTimeButton(){
        manualResultsVisibleTimeButton.click();
    }
    
    public WebElement getDeleteSessionLink(){
        return fsDeleteLink;
    }
    
    public WebElement getDeleteQuestionLink(int qnIndex){
        return browser.driver.findElement(By.xpath("//a[@onclick='deleteQuestion(" + qnIndex + ")']"));
    }
    
    public void clickEditSessionButton(){
        fsEditLink.click();
    }
    
    public void clickSaveSessionButton(){
        fsSaveLink.click();
        waitForPageToLoad();
    }
    
    public void clickquestionSaveForQuestion1(){
        questionSaveForQuestion1.click();
        waitForPageToLoad();
    }
    
    public void clickVisibilityOptionsForQuestion1(){
        browser.driver.findElement(By.className("visibilityOptionsLabel")).click();;
    }
    
    public void clickAddQuestionButton(){
        addNewQuestionButton.click();
        waitForPageToLoad();
    }
    
    public boolean clickEditQuestionButton(int qnNumber){
        WebElement qnEditLink = browser.driver.findElement(By.id("questionedittext-" + qnNumber));    
        qnEditLink.click();
        
        // Check if links toggle properly.
        WebElement qnSaveLink = browser.driver.findElement(By.id("questionsavechangestext-" + qnNumber));    
        return qnSaveLink.isDisplayed();
    }
    
    public void clickSaveExistingQuestionButton(int qnNumber){
        WebElement qnSaveLink = browser.driver.findElement(By.id("questionsavechangestext-" + qnNumber));
        qnSaveLink.click();
        waitForPageToLoad();
    }
    
    /**
     * 
     * @return {@code True} if all elements expected to be enabled
     * in the edit session frame are enabled after edit link is clicked. 
     * {@code False} if not.
     */
    public boolean verifyEditSessionBoxIsEnabled() {
        boolean isEditSessionEnabled = fsSaveLink.isDisplayed() && timezoneDropDown.isEnabled();
        isEditSessionEnabled &= neverSessionVisibleTimeButton.isEnabled();
        isEditSessionEnabled &= defaultSessionVisibleTimeButton.isEnabled();
        isEditSessionEnabled &= customSessionVisibleTimeButton.isEnabled();
        
        isEditSessionEnabled &= openSessionEmailReminderButton.isEnabled();
        isEditSessionEnabled &= closingSessionEmailReminderButton.isEnabled();
        isEditSessionEnabled &= publishedSessionEmailReminderButton.isEnabled();
        
        if (!neverSessionVisibleTimeButton.isSelected()) {
            isEditSessionEnabled &= gracePeriodDropdown.isEnabled();
            isEditSessionEnabled &= startDateBox.isEnabled() && startTimeDropdown.isEnabled();
            isEditSessionEnabled &= endDateBox.isEnabled() && endTimeDropdown.isEnabled();
            
            isEditSessionEnabled &= defaultResultsVisibleTimeButton.isEnabled();
            isEditSessionEnabled &= customResultsVisibleTimeButton.isEnabled();
            isEditSessionEnabled &= manualResultsVisibleTimeButton.isEnabled();
            isEditSessionEnabled &= neverResultsVisibleTimeButton.isEnabled();
        }
        
        return isEditSessionEnabled;
    }
    
    public boolean verifyNewEssayQuestionFormIsDisplayed() {
        return addNewQuestionButton.isDisplayed();
    }
    
    public boolean verifyNewMcqQuestionFormIsDisplayed() {
        WebElement mcqForm = browser.driver.findElement(By.id("mcqForm"));
        return mcqForm.isDisplayed() && addNewQuestionButton.isDisplayed();
    }
    
    public boolean verifyNewMsqQuestionFormIsDisplayed() {
        WebElement mcqForm = browser.driver.findElement(By.id("msqForm"));
        return mcqForm.isDisplayed() && addNewQuestionButton.isDisplayed();
    }
    
    public boolean verifyNewNumScaleQuestionFormIsDisplayed() {
        WebElement mcqForm = browser.driver.findElement(By.id("numScaleForm"));
        return mcqForm.isDisplayed() && addNewQuestionButton.isDisplayed();
    }
    
    public void selectNewQuestionType(String questionType){
        selectDropdownByVisibleValue(browser.driver.findElement(By.id("questionTypeChoice")), questionType);
    }
    
    public void selectMcqGenerateOptionsFor(String generateFor, int questionNumber){
        selectDropdownByVisibleValue(browser.driver.findElement(By.id("mcqGenerateForSelect-" + questionNumber)), generateFor);
    }
    
    public void selectMsqGenerateOptionsFor(String generateFor, int questionNumber){
        selectDropdownByVisibleValue(browser.driver.findElement(By.id("msqGenerateForSelect-" + questionNumber)), generateFor);
    }
    
    public void selectGiverTypeForQuestion1(String giverType){
        selectDropdownByVisibleValue(giverDropdownForQuestion1, giverType);
    }
    
    public void selectRecipientTypeForQuestion1(String recipientType){
        selectDropdownByVisibleValue(recipientDropdownForQuestion1, recipientType);
    }
    
    /**
     * 
     * @return {@code True} if the button was clicked successfully and an element in the new question
     * frame is now visible. {@code False} if not.
     */
    public void clickNewQuestionButton(){
        openNewQuestionButton.click();
    }
    
    /**
     * Empties the input box for the given {@code field}.
     * @param field : the ID of the field to clear.
     */
    public void clearField(String field){
        JavascriptExecutor js = (JavascriptExecutor) browser.driver;
        js.executeScript("$('#" + field
                + "')[0].value='';");
    }
    
    public void selectGiverToBeStudents() {
        selectDropdownByVisibleValue(giverDropdown, "Students in this course");
    }
    
    public void selectGiverToBeInstructors() {
        selectDropdownByVisibleValue(giverDropdown, "Instructors in this course");
    }
    
    public void selectRecipientsToBeStudents() {
        selectDropdownByVisibleValue(recipientDropdown, "Other students in the course");
    }
    
    public void selectRecipientsToBeNobodySpecific() {
        selectDropdownByVisibleValue(recipientDropdown, "Nobody specific (For general class feedback)");
    }
    
    public ArrayList<String> allContentsOfRowsInQuestionTableNewParticipateTable() {
        WebElement questionTableNew = browser.driver.findElement(By.id("questionTableNew"));
        List<WebElement> tablesInQuestionTable = questionTableNew.findElements(By.tagName("table"));
        WebElement participateTable = tablesInQuestionTable.get(tablesInQuestionTable.size() - 1);
        List<WebElement> rowsInTable = participateTable.findElements(By.tagName("tr"));
        ArrayList<String> htmlsOfRows = new ArrayList<String>();
        for (WebElement e : rowsInTable) {
            htmlsOfRows.add(e.getText());
        }
        
        return htmlsOfRows;
    }
    
    public void editFeedbackSession(
            Date startTime,
            Date endTime,
            Text instructions,
            int gracePeriod) {
        
        // Select start date
        JavascriptExecutor js = (JavascriptExecutor) browser.driver;
        js.executeScript("$('#" + Const.ParamsNames.FEEDBACK_SESSION_STARTDATE
                + "')[0].value='" + TimeHelper.formatDate(startTime) + "';");
        selectDropdownByVisibleValue(startTimeDropdown,
                TimeHelper.convertToDisplayValueInTimeDropDown(startTime));
    
        // Select deadline date
        js.executeScript("$('#" + Const.ParamsNames.FEEDBACK_SESSION_ENDDATE
                + "')[0].value='" + TimeHelper.formatDate(endTime) + "';");
        selectDropdownByVisibleValue(endTimeDropdown,
                TimeHelper.convertToDisplayValueInTimeDropDown(endTime));
        
        // Fill in instructions
        fillTextBox(instructionsTextBox, instructions.getValue());
    
        // Select grace period
        selectDropdownByVisibleValue(gracePeriodDropdown, Integer.toString(gracePeriod)+ " mins");        
    
        fsSaveLink.click();
        
        waitForPageToLoad();
    }
    
    public InstructorFeedbacksPage deleteSession() {
        clickAndConfirm(getDeleteSessionLink());
        waitForPageToLoad();
        return changePageType(InstructorFeedbacksPage.class);
    }
    
    public InstructorFeedbacksPage clickDoneEditingLink() {
        WebElement doneEditingLink = browser.driver.findElement(By.id("addNewQuestionTable")).findElement(By.tagName("a"));
        doneEditingLink.click();
        waitForPageToLoad();
        return changePageType(InstructorFeedbacksPage.class);
    }
    
    public void fillMcqOption(int optionIndex, String optionText){
        WebElement optionBox = browser.driver.findElement(By.id("mcqOption-" + optionIndex));
        fillTextBox(optionBox, optionText);
    }
    
    public void clickAddMoreMcqOptionLink(){
        WebElement addMoreOptionLink = browser.driver.findElement(By.id("mcqAddOptionLink"));
        addMoreOptionLink.click();
    }
    
    public void clickRemoveMcqOptionLink(int optionIndex, int qnIndex) {
        String idSuffix = qnIndex > 0 ? "-" + qnIndex : "";
        
        WebElement mcqOptionRow = browser.driver.findElement(By.id("mcqOptionRow-" + optionIndex + idSuffix));
        WebElement removeOptionLink = mcqOptionRow.findElement(By.id("mcqRemoveOptionLink"));
        removeOptionLink.click();
    }
    
    public void clickGenerateOptionsCheckbox(int qnIndex) {
        String idSuffix = qnIndex > 0 ? "-" + qnIndex : "";
        
        WebElement generateOptionsCheckbox = browser.driver.findElement(By.id("generateOptionsCheckbox" + idSuffix));
        generateOptionsCheckbox.click();
    }
    
    public void fillMsqOption(int optionIndex, String optionText){
        WebElement optionBox = browser.driver.findElement(By.id("msqOption-" + optionIndex));
        fillTextBox(optionBox, optionText);
    }
    
    public void clickAddMoreMsqOptionLink(){
        WebElement addMoreOptionLink = browser.driver.findElement(By.id("msqAddOptionLink"));
        addMoreOptionLink.click();
    }
    
    public void clickRemoveMsqOptionLink(int optionIndex, int qnIndex) {
        String idSuffix = qnIndex > 0 ? "-" + qnIndex : "";
        
        WebElement msqOptionRow = browser.driver.findElement(By.id("msqOptionRow-" + optionIndex + idSuffix));
        WebElement removeOptionLink = msqOptionRow.findElement(By.id("msqRemoveOptionLink"));
        removeOptionLink.click();
    }
    
    public FeedbackSubmitPage clickPreviewAsStudentButton() {
        previewAsStudentButton.click();
        waitForPageToLoad();
        switchToNewWindow();
        return changePageType(FeedbackSubmitPage.class);
    }
    
    public FeedbackSubmitPage clickPreviewAsInstructorButton() {
        previewAsInstructorButton.click();
        waitForPageToLoad();
        switchToNewWindow();
        return changePageType(FeedbackSubmitPage.class);
    }
    
    public void clickGetLinkButton() {
        getLinkButton.click();
    }
}
