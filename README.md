# selenium-api-work
Main Class: TestNgCalling.java



#Xpath Tutorial

###Basic Xpath:
Xpath=//input[@name='uid']
###Contains():
	Xpath=//*[contains(@type,'sub')]
###Using OR & AND:
	Xpath=//*[@type='submit' or @name='btnReset']
    Xpath=//input[@type='submit' and @name='btnLogin']
###Xpath Starts-with
	Xpath=//label[starts-with(@id,'message')]
###XPath Text() Function
	Xpath=//td[text()='UserID']
##XPath axes methods:
###Following:
    Selects all elements in the document of the current node( )
    Xpath=//*[@type='text']//following::input
###Ancestor:
	The ancestor axis selects all ancestors element (grandparent, parent, etc.)
	Xpath=//*[text()='Enterprise Testing']//ancestor::div
###Child:
	Selects all children elements of the current node.
	Xpath=//*[@id='java_technologies']//child::li
###Preceding:
	Select all nodes that come before the current node.
	Xpath=//*[@type='submit']//preceding::input 
###Following-sibling:
	Select the following siblings of the context node. Siblings are at the same level of the current node.
	xpath=//*[@type='submit']//following-sibling::input
###Parent
    Selects the parent of the current node
    Xpath=//*[@id='rt-feature']//parent::div
###Descendant:
    Selects the descendants of the current node
    Xpath=//*[@id='rt-feature']//descendant::a
 

