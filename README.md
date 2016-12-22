# lib-mflow
Android Framework - Mortar &amp; Flow extension

The lib-mflow library was created a few years ago (2013/2014). This version 
servers on github currently servers only as a demo using libraries (old versions) Mortar and Flow
Please keep in a mind, that library is using these versions of main libs:

* Mortar 0.16
* Flow 0.7
* Dagger 1.2.1

The main purpose of the library (why I created it) is to bring the MVP pattern for Android, which 
provides libraries Mortar + Flow (MFow).

In short the **Flow** is a small library that helps with describing an app as a collection of 
moderately independent screens. These screens can be pushed onto a concrete backstack to provide navigation history.
If Flow tells you where to go, Mortar tells you what to build when you get there. Mortar is a 
simple library that makes it easy to pair thin views with dedicated controllers/presenters, 
isolated from most of the vagaries of the Activity life cycle.

**Mortar** is awesome framework which makes our live easier. But Mortar doesn't contain any abstract or parent activity - this is mission of this library => Mortar and Flow must work together.
Your work is only implement:

* MainView - parent view that displays subviews
* MainPresenter
* MainScreen

### Why this library has been created (in 2013)?

The Model-View-Controller (MVC) pattern manifests in many different flavors across various 
application environments.
In Android, the commonly used interpretation delegates activities and fragments as the controllers, 
XML layout files as views, and Java objects as the models.
Our problem with this interpretation lies with the controllers and their tendency to bloat with 
logic for updating views. We wanted to take a closer look at how we could improve this pattern for 
our applications.

4x I:

* I tried to find existing solution and we found it! Existing solution is using Mortar, Flow and 
Dagger libraries which are develop by Square company!
* I believe that Mortar + Flow + Dagger are potential game changer for MVC in Android.
* I joined Mortar +Flow + Dagger and result is the lib-mflow!
* I do not use MVC but we call it MVP like Square inc. call it!

During implementation of a big application where each processes (function) could be a separate app
 I found a lot of problems (troubles) with implementation in Android default way.
 
* Code is not readable for developers who are not familiar with code
* Intents are too fragile
* Communication between components is complex 
* Creating SDK for application will be difficult
 
#### Code is not readable for developers who are not familiar with code

* Every app uses activities as if they are view controllers.
* Lifecycle of an activity is quite complex

##### Solution

The Square Inc has recently released a new library called Mortar which divide Android apps into composable modules.
Mortar give to us a very simple interface for creating view controllers (presenters as they call them).


#### Intents are too fragile

Intents are generally great for inter-app operability. It’s what allows you to change default apps, 
send and receive data from other apps (for example, accessing the Barcode Scanner app to scan QR codes), 
and even write plug-ins for other apps

However, it’s also what apps must use internally to jump from activity to activity. 
This means that any parameter you want to pass to an activity needs to be serialized in a bundle 
and manually pulled  out and validated when the activity starts.
The same problem applies to fragments, you must always use an empty constructor when creating a 
fragment and pass in the arguments as a bundle. 

```java
public class EanItemSearchFragment extends BaseCustomKeyboardSupportFragment implements ItemSearchWorkerFragment.IOnItemSearchWorkerFragmentCallback,
        ItemSearchCard.IOnItemSearchCardCallback,DatawedgeDelegate {
   ...
 
  private static final String ARGUMENT_ITEM = "item";
  private static final String ARGUMENT_ITEM_NAME = "itemName";
  private static final String ARGUMENT_ITEM_SEARCH_DIAG_TITLE_RES_ID = "searchDiagTitle";
  private static final String ARGUMENT_ITEM_NAME_COLOR_RES_ID = "itmNameColorResID";
  private static final String ARGUMENT_REGISTRATION_NR = "RegistrationNr";
 
  ...
 
  public static EanItemSearchFragment newInstance(String itemOrEanID, String itemName, Integer itemSearchDialogTitle) {
    if (itemSearchDialogTitle != null) {
      return createFragment(itemOrEanID, itemName, itemSearchDialogTitle.intValue());
    } else {
      return createFragment(itemOrEanID, itemName, R.string.title_item_search_dialog);
    }
  }
 
  public static EanItemSearchFragment newInstance(int itemSearchDialogTitle, Integer itemNameColorResID) {
    return createFragment(null, null, itemSearchDialogTitle, itemNameColorResID,null);
  }
 
  public static EanItemSearchFragment newInstance() {
    return new EanItemSearchFragment();
  }
 
  private static EanItemSearchFragment createFragment(String itemOrEanID, String itemName, int itemSearchTitleResID) {
    return EanItemSearchFragment.createFragment(itemOrEanID, itemName, itemSearchTitleResID, null,null);
  }
 
  private static EanItemSearchFragment createFragment(String itemOrEanID, String itemName, int itemSearchTitleResID,
                                                      Integer itemNameColorResID, String registeredNumber) {
    Bundle arguments = new Bundle();
    arguments.putInt(ARGUMENT_ITEM_SEARCH_DIAG_TITLE_RES_ID, itemSearchTitleResID);
    if (!TextUtils.isEmpty(itemName)) {
      arguments.putString(ARGUMENT_ITEM_NAME, itemName);
    }
    if (!TextUtils.isEmpty(itemOrEanID)) {
      arguments.putString(ARGUMENT_ITEM, itemOrEanID);
    }
    if (itemNameColorResID != null) {
      arguments.putInt(ARGUMENT_ITEM_NAME_COLOR_RES_ID, itemNameColorResID);
    }
    if(registeredNumber!=null && !TextUtils.isEmpty(registeredNumber)){
      arguments.putString(ARGUMENT_REGISTRATION_NR,registeredNumber);
    }
    EanItemSearchFragment fragment = new EanItemSearchFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

}
```

##### Solution

Once again - **Square** has a much better solution for this called **Flow** _which is a small 
library that helps to decouple app as a collection of independet screens and avoids all the intent 
nonsense by just using simple constructor lke so:_

```java
flow.goTo(new RegistrationScreen(customer, orders))
```

#### Communication between components is complex
In big app we must define (in a standard Android way) a lot of callbacks (interfaces as listeners). 
Output screen looks very simple for user => but code inside of activity is crazy for instance:]
```java
public class DirectOrderPositionRegistrationActivity extends BaseActivity implements IOnItemSearchCallback,
        DirectOrderFillPositionWorkerFragment.IOnDirectOrderDataCallback, IOnAlertDialogCallback,
        PrepareDataInContainerWorkerFragment.IOnPrepareDataInContainerWorkerFragmentCallback,
        SupplierItemSelectionDialogFragment.IOnSupplierItemSelectionCallback, QuantityUomFragment.IOnBaseItemInfoCallback,
        ItemCollectionSelectionDialogFragment.IOnItemCollectionSelectionCallback {
}
```
By using MFlow we removed this callback hell and separate it to the nested presenters and views.


### What is MVP?

**MVP** (**M**odel **V**iew **P**resenter) pattern is a **derivative from the well known MVC** 
(Model View Controller), which for a while now is gaining importance in the development of Android 
applications. There are more and more people talking about it, but yet very few reliable and 
structured information.
 
The MVP pattern allows **separate the presentation layer from the logic**, so that everything about 
how the interface works is separated from how we represent it on screen. Ideally the MVP pattern 
would achieve that same logic might have completely different and interchangeable views.
 
First thing to clarify is that MVP **is not an architectural pattern**, it’s only responsible for 
the presentation layer.

