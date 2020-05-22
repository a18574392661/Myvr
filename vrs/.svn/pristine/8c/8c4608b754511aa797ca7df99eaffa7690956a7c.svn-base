(function(w){
// 空函数
 function shield(){
 	return false;
 }
//document.addEventListener('touchstart', shield, false);//取消浏览器的所有事件，使得active的样式在手机上正常生效
//document.oncontextmenu=shield;//屏蔽选择函数
// H5 plus事件处理
var ws=null,as='pop-in';
function plusReady(){
	ws=plus.webview.currentWebview();
	plus.key.addEventListener('backbutton', function(){
		back();
	},false);
}
if(w.plus){
	plusReady();
}else{
	document.addEventListener('plusready', plusReady, false);
}
// DOMContentLoaded事件处理
document.addEventListener('DOMContentLoaded', function(){
	gInit();
	//document.body.onselectstart=shield;
},false);
// 返回
w.back=function(hide){
	if(w.plus){
		ws||(ws=plus.webview.currentWebview());
		(hide||ws.preate)?ws.hide('auto'):ws.close('auto');
	}else if(history.length>1){
		history.back();
	}else{
		w.close();
	}
};
// 处理点击事件
var openw=null;
/**
 * 打开新窗口
 * @param {URIString} id : 要打开页面url
 * @param {String} t : 页面标题名称
 * @param {JSON} ws : Webview窗口属性
 */
w.clicked=function(id, t, ws){
	if(openw){//避免多次打开同一个页面
		return null;
	}
	if(w.plus){
		ws=ws||{};
		ws.scrollIndicator||(ws.scrollIndicator='none');
		ws.scalable||(ws.scalable=false);
		ws.backButtonAutoControl||(ws.backButtonAutoControl='close');
		ws.titleNView=ws.titleNView||{autoBackButton:true};
		ws.titleNView.backgroundColor = '#0077EB';
		ws.titleNView.titleColor = '#ffffff';
		ws.doc&&(ws.titleNView.buttons=ws.titleNView.buttons||[],ws.titleNView.buttons.push({fontSrc:'_www/helloh5.ttf',text:'\ue301',fontSize:'20px',onclick:'javascript:openDoc()'}));
		t&&(ws.titleNView.titleText=t);
		openw = plus.webview.create(id, id.substring(id.lastIndexOf('/')+1, id.indexOf("?") != -1 ? id.indexOf("?") : id.length ), ws);
		openw.addEventListener('loaded', function(){
			openw.show(as);
		}, false);
		openw.addEventListener('close', function(){
			openw=null;
		}, false);
		return openw;
	}else{
		w.open(id);
	}
	return null;
};
/**
 * 创建新窗口（无原始标题栏），
 * @param {URIString} id : 要打开页面url
 * @param {JSON} ws : Webview窗口属性
 */
w.createWithoutTitle=function(id, ws){
	if(openw){//避免多次打开同一个页面
		return null;
	}
	if(w.plus){
		ws=ws||{};
		ws.scrollIndicator||(ws.scrollIndicator='none');
		ws.scalable||(ws.scalable=false);
		ws.backButtonAutoControl||(ws.backButtonAutoControl='close');
		openw = plus.webview.create(id, id, ws);
		openw.addEventListener('close', function(){
			openw=null;
		}, false);
		return openw;
	}else{
		w.open(id);
	}
	return null;
};
/**
 * 打开文档页面
 * @param {URIString} c : 要打开页面url
 */
w.openDoc=function(c){
	plus.webview.create(c, 'document', {
		titleNView:{
			autoBackButton:true,
			backgroundColor:'#D74B28',
			titleColor:'#CCCCCC'
		},
		backButtonAutoControl:'close',
		scalable:false
	}).show('pop-in');
};
/**
 * 兼容提示

w.compatibleConfirm=function(){
	plus.nativeUI.confirm('本OS原生层面不提供该控件，需使用mui框架实现类似效果。请点击“确定”下载Hello mui示例',function(e){
		if(0==e.index){
			plus.runtime.openURL("http://www.dcloud.io/hellomui/");
		}
	},"",["确定","取消"]);
} */
// 通用元素对象
var _dout_=null;
w.gInit=function(){
	_dout_=document.getElementById("output");
};
// 清空输出内容
w.outClean=function(){
	_dout_.innerText="";
	_dout_.scrollTop=0;//在iOS8存在不滚动的现象
};
// 输出内容
w.outSet=function(s){
	console.log(s);
	_dout_.innerText=s+"\n";
	(0==_dout_.scrollTop)&&(_dout_.scrollTop=1);//在iOS8存在不滚动的现象
};
// 输出行内容
w.outLine=function(s){
	console.log(s);
	_dout_.innerText+=s+"\n";
	(0==_dout_.scrollTop)&&(_dout_.scrollTop=1);//在iOS8存在不滚动的现象
};
// 格式化时长字符串，格式为"HH:MM:SS"
w.timeToStr=function(ts){
	if(isNaN(ts)){
		return "--:--:--";
	}
	var h=parseInt(ts/3600);
	var m=parseInt((ts%3600)/60);
	var s=parseInt(ts%60);
	return (ultZeroize(h)+":"+ultZeroize(m)+":"+ultZeroize(s));
};
// 格式化日期时间字符串，格式为"YYYY-MM-DD HH:MM:SS"
w.dateToStr=function(d){
	return (d.getFullYear()+"-"+ultZeroize(d.getMonth()+1)+"-"+ultZeroize(d.getDate())+" "+ultZeroize(d.getHours())+":"+ultZeroize(d.getMinutes())+":"+ultZeroize(d.getSeconds()));
};
/**
 * zeroize value with length(default is 2).
 * @param {Object} v
 * @param {Number} l
 * @return {String} 
 */
w.ultZeroize=function(v,l){
	var z="";
	l=l||2;
	v=String(v);
	for(var i=0;i<l-v.length;i++){
		z+="0";
	}
	return z+v;
};
})(window);

// fast click 
;(function () {
	'use strict';

	/**
	 * @preserve FastClick: polyfill to remove click delays on browsers with touch UIs.
	 *
	 * @codingstandard ftlabs-jsv2
	 * @copyright The Financial Times Limited [All Rights Reserved]
	 * @license MIT License (see LICENSE.txt)
	 */

	/*jslint browser:true, node:true*/
	/*global define, Event, Node*/


	/**
	 * Instantiate fast-clicking listeners on the specified layer.
	 *
	 * @constructor
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	function FastClick(layer, options) {
		var oldOnClick;

		options = options || {};

		/**
		 * Whether a click is currently being tracked.
		 *
		 * @type boolean
		 */
		this.trackingClick = false;


		/**
		 * Timestamp for when click tracking started.
		 *
		 * @type number
		 */
		this.trackingClickStart = 0;


		/**
		 * The element being tracked for a click.
		 *
		 * @type EventTarget
		 */
		this.targetElement = null;


		/**
		 * X-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartX = 0;


		/**
		 * Y-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartY = 0;


		/**
		 * ID of the last touch, retrieved from Touch.identifier.
		 *
		 * @type number
		 */
		this.lastTouchIdentifier = 0;


		/**
		 * Touchmove boundary, beyond which a click will be cancelled.
		 *
		 * @type number
		 */
		this.touchBoundary = options.touchBoundary || 10;


		/**
		 * The FastClick layer.
		 *
		 * @type Element
		 */
		this.layer = layer;

		/**
		 * The minimum time between tap(touchstart and touchend) events
		 *
		 * @type number
		 */
		this.tapDelay = options.tapDelay || 200;

		/**
		 * The maximum time for a tap
		 *
		 * @type number
		 */
		this.tapTimeout = options.tapTimeout || 700;

		if (FastClick.notNeeded(layer)) {
			return;
		}

		// Some old versions of Android don't have Function.prototype.bind
		function bind(method, context) {
			return function() { return method.apply(context, arguments); };
		}


		var methods = ['onMouse', 'onClick', 'onTouchStart', 'onTouchMove', 'onTouchEnd', 'onTouchCancel'];
		var context = this;
		for (var i = 0, l = methods.length; i < l; i++) {
			context[methods[i]] = bind(context[methods[i]], context);
		}

		// Set up event handlers as required
		if (deviceIsAndroid) {
			layer.addEventListener('mouseover', this.onMouse, true);
			layer.addEventListener('mousedown', this.onMouse, true);
			layer.addEventListener('mouseup', this.onMouse, true);
		}

		layer.addEventListener('click', this.onClick, true);
		layer.addEventListener('touchstart', this.onTouchStart, false);
		layer.addEventListener('touchmove', this.onTouchMove, false);
		layer.addEventListener('touchend', this.onTouchEnd, false);
		layer.addEventListener('touchcancel', this.onTouchCancel, false);

		// Hack is required for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
		// which is how FastClick normally stops click events bubbling to callbacks registered on the FastClick
		// layer when they are cancelled.
		if (!Event.prototype.stopImmediatePropagation) {
			layer.removeEventListener = function(type, callback, capture) {
				var rmv = Node.prototype.removeEventListener;
				if (type === 'click') {
					rmv.call(layer, type, callback.hijacked || callback, capture);
				} else {
					rmv.call(layer, type, callback, capture);
				}
			};

			layer.addEventListener = function(type, callback, capture) {
				var adv = Node.prototype.addEventListener;
				if (type === 'click') {
					adv.call(layer, type, callback.hijacked || (callback.hijacked = function(event) {
						if (!event.propagationStopped) {
							callback(event);
						}
					}), capture);
				} else {
					adv.call(layer, type, callback, capture);
				}
			};
		}

		// If a handler is already declared in the element's onclick attribute, it will be fired before
		// FastClick's onClick handler. Fix this by pulling out the user-defined handler function and
		// adding it as listener.
		if (typeof layer.onclick === 'function') {

			// Android browser on at least 3.2 requires a new reference to the function in layer.onclick
			// - the old one won't work if passed to addEventListener directly.
			oldOnClick = layer.onclick;
			layer.addEventListener('click', function(event) {
				oldOnClick(event);
			}, false);
			layer.onclick = null;
		}
	}

	/**
	* Windows Phone 8.1 fakes user agent string to look like Android and iPhone.
	*
	* @type boolean
	*/
	var deviceIsWindowsPhone = navigator.userAgent.indexOf("Windows Phone") >= 0;

	/**
	 * Android requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsAndroid = navigator.userAgent.indexOf('Android') > 0 && !deviceIsWindowsPhone;


	/**
	 * iOS requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsIOS = /iP(ad|hone|od)/.test(navigator.userAgent) && !deviceIsWindowsPhone;


	/**
	 * iOS 4 requires an exception for select elements.
	 *
	 * @type boolean
	 */
	var deviceIsIOS4 = deviceIsIOS && (/OS 4_\d(_\d)?/).test(navigator.userAgent);


	/**
	 * iOS 6.0-7.* requires the target element to be manually derived
	 *
	 * @type boolean
	 */
	var deviceIsIOSWithBadTarget = deviceIsIOS && (/OS [6-7]_\d/).test(navigator.userAgent);

	/**
	 * BlackBerry requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsBlackBerry10 = navigator.userAgent.indexOf('BB10') > 0;

	/**
	 * Determine whether a given element requires a native click.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element needs a native click
	 */
	FastClick.prototype.needsClick = function(target) {
		switch (target.nodeName.toLowerCase()) {

		// Don't send a synthetic click to disabled inputs (issue #62)
		case 'button':
		case 'select':
		case 'textarea':
			if (target.disabled) {
				return true;
			}

			break;
		case 'input':

			// File inputs need real clicks on iOS 6 due to a browser bug (issue #68)
			if ((deviceIsIOS && target.type === 'file') || target.disabled) {
				return true;
			}

			break;
		case 'label':
		case 'iframe': // iOS8 homescreen apps can prevent events bubbling into frames
		case 'video':
			return true;
		}

		return (/\bneedsclick\b/).test(target.className);
	};


	/**
	 * Determine whether a given element requires a call to focus to simulate click into element.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element requires a call to focus to simulate native click.
	 */
	FastClick.prototype.needsFocus = function(target) {
		switch (target.nodeName.toLowerCase()) {
		case 'textarea':
			return true;
		case 'select':
			return !deviceIsAndroid;
		case 'input':
			switch (target.type) {
			case 'button':
			case 'checkbox':
			case 'file':
			case 'image':
			case 'radio':
			case 'submit':
				return false;
			}

			// No point in attempting to focus disabled inputs
			return !target.disabled && !target.readOnly;
		default:
			return (/\bneedsfocus\b/).test(target.className);
		}
	};


	/**
	 * Send a click event to the specified element.
	 *
	 * @param {EventTarget|Element} targetElement
	 * @param {Event} event
	 */
	FastClick.prototype.sendClick = function(targetElement, event) {
		var clickEvent, touch;

		// On some Android devices activeElement needs to be blurred otherwise the synthetic click will have no effect (#24)
		if (document.activeElement && document.activeElement !== targetElement) {
			document.activeElement.blur();
		}

		touch = event.changedTouches[0];

		// Synthesise a click event, with an extra attribute so it can be tracked
		clickEvent = document.createEvent('MouseEvents');
		clickEvent.initMouseEvent(this.determineEventType(targetElement), true, true, window, 1, touch.screenX, touch.screenY, touch.clientX, touch.clientY, false, false, false, false, 0, null);
		clickEvent.forwardedTouchEvent = true;
		targetElement.dispatchEvent(clickEvent);
	};

	FastClick.prototype.determineEventType = function(targetElement) {

		//Issue #159: Android Chrome Select Box does not open with a synthetic click event
		if (deviceIsAndroid && targetElement.tagName.toLowerCase() === 'select') {
			return 'mousedown';
		}

		return 'click';
	};


	/**
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.focus = function(targetElement) {
		var length;

		// Issue #160: on iOS 7, some input elements (e.g. date datetime month) throw a vague TypeError on setSelectionRange. These elements don't have an integer value for the selectionStart and selectionEnd properties, but unfortunately that can't be used for detection because accessing the properties also throws a TypeError. Just check the type instead. Filed as Apple bug #15122724.
		if (deviceIsIOS && targetElement.setSelectionRange && targetElement.type.indexOf('date') !== 0 && targetElement.type !== 'time' && targetElement.type !== 'month') {
			length = targetElement.value.length;
			targetElement.setSelectionRange(length, length);
		} else {
			targetElement.focus();
		}
	};


	/**
	 * Check whether the given target element is a child of a scrollable layer and if so, set a flag on it.
	 *
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.updateScrollParent = function(targetElement) {
		var scrollParent, parentElement;

		scrollParent = targetElement.fastClickScrollParent;

		// Attempt to discover whether the target element is contained within a scrollable layer. Re-check if the
		// target element was moved to another parent.
		if (!scrollParent || !scrollParent.contains(targetElement)) {
			parentElement = targetElement;
			do {
				if (parentElement.scrollHeight > parentElement.offsetHeight) {
					scrollParent = parentElement;
					targetElement.fastClickScrollParent = parentElement;
					break;
				}

				parentElement = parentElement.parentElement;
			} while (parentElement);
		}

		// Always update the scroll top tracker if possible.
		if (scrollParent) {
			scrollParent.fastClickLastScrollTop = scrollParent.scrollTop;
		}
	};


	/**
	 * @param {EventTarget} targetElement
	 * @returns {Element|EventTarget}
	 */
	FastClick.prototype.getTargetElementFromEventTarget = function(eventTarget) {

		// On some older browsers (notably Safari on iOS 4.1 - see issue #56) the event target may be a text node.
		if (eventTarget.nodeType === Node.TEXT_NODE) {
			return eventTarget.parentNode;
		}

		return eventTarget;
	};


	/**
	 * On touch start, record the position and scroll offset.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchStart = function(event) {
		var targetElement, touch, selection;

		// Ignore multiple touches, otherwise pinch-to-zoom is prevented if both fingers are on the FastClick element (issue #111).
		if (event.targetTouches.length > 1) {
			return true;
		}

		targetElement = this.getTargetElementFromEventTarget(event.target);
		touch = event.targetTouches[0];

		if (deviceIsIOS) {

			// Only trusted events will deselect text on iOS (issue #49)
			selection = window.getSelection();
			if (selection.rangeCount && !selection.isCollapsed) {
				return true;
			}

			if (!deviceIsIOS4) {

				// Weird things happen on iOS when an alert or confirm dialog is opened from a click event callback (issue #23):
				// when the user next taps anywhere else on the page, new touchstart and touchend events are dispatched
				// with the same identifier as the touch event that previously triggered the click that triggered the alert.
				// Sadly, there is an issue on iOS 4 that causes some normal touch events to have the same identifier as an
				// immediately preceeding touch event (issue #52), so this fix is unavailable on that platform.
				// Issue 120: touch.identifier is 0 when Chrome dev tools 'Emulate touch events' is set with an iOS device UA string,
				// which causes all touch events to be ignored. As this block only applies to iOS, and iOS identifiers are always long,
				// random integers, it's safe to to continue if the identifier is 0 here.
				if (touch.identifier && touch.identifier === this.lastTouchIdentifier) {
					event.preventDefault();
					return false;
				}

				this.lastTouchIdentifier = touch.identifier;

				// If the target element is a child of a scrollable layer (using -webkit-overflow-scrolling: touch) and:
				// 1) the user does a fling scroll on the scrollable layer
				// 2) the user stops the fling scroll with another tap
				// then the event.target of the last 'touchend' event will be the element that was under the user's finger
				// when the fling scroll was started, causing FastClick to send a click event to that layer - unless a check
				// is made to ensure that a parent layer was not scrolled before sending a synthetic click (issue #42).
				this.updateScrollParent(targetElement);
			}
		}

		this.trackingClick = true;
		this.trackingClickStart = event.timeStamp;
		this.targetElement = targetElement;

		this.touchStartX = touch.pageX;
		this.touchStartY = touch.pageY;

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			event.preventDefault();
		}

		return true;
	};


	/**
	 * Based on a touchmove event object, check whether the touch has moved past a boundary since it started.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.touchHasMoved = function(event) {
		var touch = event.changedTouches[0], boundary = this.touchBoundary;

		if (Math.abs(touch.pageX - this.touchStartX) > boundary || Math.abs(touch.pageY - this.touchStartY) > boundary) {
			return true;
		}

		return false;
	};


	/**
	 * Update the last position.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchMove = function(event) {
		if (!this.trackingClick) {
			return true;
		}

		// If the touch has moved, cancel the click tracking
		if (this.targetElement !== this.getTargetElementFromEventTarget(event.target) || this.touchHasMoved(event)) {
			this.trackingClick = false;
			this.targetElement = null;
		}

		return true;
	};


	/**
	 * Attempt to find the labelled control for the given label element.
	 *
	 * @param {EventTarget|HTMLLabelElement} labelElement
	 * @returns {Element|null}
	 */
	FastClick.prototype.findControl = function(labelElement) {

		// Fast path for newer browsers supporting the HTML5 control attribute
		if (labelElement.control !== undefined) {
			return labelElement.control;
		}

		// All browsers under test that support touch events also support the HTML5 htmlFor attribute
		if (labelElement.htmlFor) {
			return document.getElementById(labelElement.htmlFor);
		}

		// If no for attribute exists, attempt to retrieve the first labellable descendant element
		// the list of which is defined here: http://www.w3.org/TR/html5/forms.html#category-label
		return labelElement.querySelector('button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea');
	};


	/**
	 * On touch end, determine whether to send a click event at once.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchEnd = function(event) {
		var forElement, trackingClickStart, targetTagName, scrollParent, touch, targetElement = this.targetElement;

		if (!this.trackingClick) {
			return true;
		}

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			this.cancelNextClick = true;
			return true;
		}

		if ((event.timeStamp - this.trackingClickStart) > this.tapTimeout) {
			return true;
		}

		// Reset to prevent wrong click cancel on input (issue #156).
		this.cancelNextClick = false;

		this.lastClickTime = event.timeStamp;

		trackingClickStart = this.trackingClickStart;
		this.trackingClick = false;
		this.trackingClickStart = 0;

		// On some iOS devices, the targetElement supplied with the event is invalid if the layer
		// is performing a transition or scroll, and has to be re-detected manually. Note that
		// for this to function correctly, it must be called *after* the event target is checked!
		// See issue #57; also filed as rdar://13048589 .
		if (deviceIsIOSWithBadTarget) {
			touch = event.changedTouches[0];

			// In certain cases arguments of elementFromPoint can be negative, so prevent setting targetElement to null
			targetElement = document.elementFromPoint(touch.pageX - window.pageXOffset, touch.pageY - window.pageYOffset) || targetElement;
			targetElement.fastClickScrollParent = this.targetElement.fastClickScrollParent;
		}

		targetTagName = targetElement.tagName.toLowerCase();
		if (targetTagName === 'label') {
			forElement = this.findControl(targetElement);
			if (forElement) {
				this.focus(targetElement);
				if (deviceIsAndroid) {
					return false;
				}

				targetElement = forElement;
			}
		} else if (this.needsFocus(targetElement)) {

			// Case 1: If the touch started a while ago (best guess is 100ms based on tests for issue #36) then focus will be triggered anyway. Return early and unset the target element reference so that the subsequent click will be allowed through.
			// Case 2: Without this exception for input elements tapped when the document is contained in an iframe, then any inputted text won't be visible even though the value attribute is updated as the user types (issue #37).
			if ((event.timeStamp - trackingClickStart) > 100 || (deviceIsIOS && window.top !== window && targetTagName === 'input')) {
				this.targetElement = null;
				return false;
			}

			this.focus(targetElement);
			this.sendClick(targetElement, event);

			// Select elements need the event to go through on iOS 4, otherwise the selector menu won't open.
			// Also this breaks opening selects when VoiceOver is active on iOS6, iOS7 (and possibly others)
			if (!deviceIsIOS || targetTagName !== 'select') {
				this.targetElement = null;
				event.preventDefault();
			}

			return false;
		}

		if (deviceIsIOS && !deviceIsIOS4) {

			// Don't send a synthetic click event if the target element is contained within a parent layer that was scrolled
			// and this tap is being used to stop the scrolling (usually initiated by a fling - issue #42).
			scrollParent = targetElement.fastClickScrollParent;
			if (scrollParent && scrollParent.fastClickLastScrollTop !== scrollParent.scrollTop) {
				return true;
			}
		}

		// Prevent the actual click from going though - unless the target node is marked as requiring
		// real clicks or if it is in the whitelist in which case only non-programmatic clicks are permitted.
		if (!this.needsClick(targetElement)) {
			event.preventDefault();
			this.sendClick(targetElement, event);
		}

		return false;
	};


	/**
	 * On touch cancel, stop tracking the click.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.onTouchCancel = function() {
		this.trackingClick = false;
		this.targetElement = null;
	};


	/**
	 * Determine mouse events which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onMouse = function(event) {

		// If a target element was never set (because a touch event was never fired) allow the event
		if (!this.targetElement) {
			return true;
		}

		if (event.forwardedTouchEvent) {
			return true;
		}

		// Programmatically generated events targeting a specific element should be permitted
		if (!event.cancelable) {
			return true;
		}

		// Derive and check the target element to see whether the mouse event needs to be permitted;
		// unless explicitly enabled, prevent non-touch click events from triggering actions,
		// to prevent ghost/doubleclicks.
		if (!this.needsClick(this.targetElement) || this.cancelNextClick) {

			// Prevent any user-added listeners declared on FastClick element from being fired.
			if (event.stopImmediatePropagation) {
				event.stopImmediatePropagation();
			} else {

				// Part of the hack for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
				event.propagationStopped = true;
			}

			// Cancel the event
			event.stopPropagation();
			event.preventDefault();

			return false;
		}

		// If the mouse event is permitted, return true for the action to go through.
		return true;
	};


	/**
	 * On actual clicks, determine whether this is a touch-generated click, a click action occurring
	 * naturally after a delay after a touch (which needs to be cancelled to avoid duplication), or
	 * an actual click which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onClick = function(event) {
		var permitted;

		// It's possible for another FastClick-like library delivered with third-party code to fire a click event before FastClick does (issue #44). In that case, set the click-tracking flag back to false and return early. This will cause onTouchEnd to return early.
		if (this.trackingClick) {
			this.targetElement = null;
			this.trackingClick = false;
			return true;
		}

		// Very odd behaviour on iOS (issue #18): if a submit element is present inside a form and the user hits enter in the iOS simulator or clicks the Go button on the pop-up OS keyboard the a kind of 'fake' click event will be triggered with the submit-type input element as the target.
		if (event.target.type === 'submit' && event.detail === 0) {
			return true;
		}

		permitted = this.onMouse(event);

		// Only unset targetElement if the click is not permitted. This will ensure that the check for !targetElement in onMouse fails and the browser's click doesn't go through.
		if (!permitted) {
			this.targetElement = null;
		}

		// If clicks are permitted, return true for the action to go through.
		return permitted;
	};


	/**
	 * Remove all FastClick's event listeners.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.destroy = function() {
		var layer = this.layer;

		if (deviceIsAndroid) {
			layer.removeEventListener('mouseover', this.onMouse, true);
			layer.removeEventListener('mousedown', this.onMouse, true);
			layer.removeEventListener('mouseup', this.onMouse, true);
		}

		layer.removeEventListener('click', this.onClick, true);
		layer.removeEventListener('touchstart', this.onTouchStart, false);
		layer.removeEventListener('touchmove', this.onTouchMove, false);
		layer.removeEventListener('touchend', this.onTouchEnd, false);
		layer.removeEventListener('touchcancel', this.onTouchCancel, false);
	};


	/**
	 * Check whether FastClick is needed.
	 *
	 * @param {Element} layer The layer to listen on
	 */
	FastClick.notNeeded = function(layer) {
		var metaViewport;
		var chromeVersion;
		var blackberryVersion;
		var firefoxVersion;

		// Devices that don't support touch don't need FastClick
		if (typeof window.ontouchstart === 'undefined') {
			return true;
		}

		// Chrome version - zero for other browsers
		chromeVersion = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (chromeVersion) {

			if (deviceIsAndroid) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// Chrome on Android with user-scalable="no" doesn't need FastClick (issue #89)
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// Chrome 32 and above with width=device-width or less don't need FastClick
					if (chromeVersion > 31 && document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}

			// Chrome desktop doesn't need FastClick (issue #15)
			} else {
				return true;
			}
		}

		if (deviceIsBlackBerry10) {
			blackberryVersion = navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/);

			// BlackBerry 10.3+ does not require Fastclick library.
			// https://github.com/ftlabs/fastclick/issues/251
			if (blackberryVersion[1] >= 10 && blackberryVersion[2] >= 3) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// user-scalable=no eliminates click delay.
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// width=device-width (or less than device-width) eliminates click delay.
					if (document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}
			}
		}

		// IE10 with -ms-touch-action: none or manipulation, which disables double-tap-to-zoom (issue #97)
		if (layer.style.msTouchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		// Firefox version - zero for other browsers
		firefoxVersion = +(/Firefox\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (firefoxVersion >= 27) {
			// Firefox 27+ does not have tap delay if the content is not zoomable - https://bugzilla.mozilla.org/show_bug.cgi?id=922896

			metaViewport = document.querySelector('meta[name=viewport]');
			if (metaViewport && (metaViewport.content.indexOf('user-scalable=no') !== -1 || document.documentElement.scrollWidth <= window.outerWidth)) {
				return true;
			}
		}

		// IE11: prefixed -ms-touch-action is no longer supported and it's recomended to use non-prefixed version
		// http://msdn.microsoft.com/en-us/library/windows/apps/Hh767313.aspx
		if (layer.style.touchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		return false;
	};


	/**
	 * Factory method for creating a FastClick object
	 *
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	FastClick.attach = function(layer, options) {
		return new FastClick(layer, options);
	};


	if (typeof define === 'function' && typeof define.amd === 'object' && define.amd) {

		// AMD. Register as an anonymous module.
		define(function() {
			return FastClick;
		});
	} else if (typeof module !== 'undefined' && module.exports) {
		module.exports = FastClick.attach;
		module.exports.FastClick = FastClick;
	} else {
		window.FastClick = FastClick;
	}

document.addEventListener('DOMContentLoaded', function() {
    FastClick.attach(document.body);
}, false);

}());

/* var ws=null,as='pop-in'
// 处理点击事件
var openw=null;
function openWindow (id, t, ws){
	if(openw){//避免多次打开同一个页面
		return null;
	}
	if(window.plus){
		ws=ws||{};
		ws.scrollIndicator||(ws.scrollIndicator='none');
		ws.scalable||(ws.scalable=false);
		ws.backButtonAutoControl||(ws.backButtonAutoControl='close');
		ws.titleNView=ws.titleNView||{autoBackButton:true};
		ws.titleNView.backgroundColor = '#0077EB';
		ws.titleNView.titleColor = '#ffffff';
		ws.doc&&(ws.titleNView.buttons=ws.titleNView.buttons||[],ws.titleNView.buttons.push({fontSrc:'_www/helloh5.ttf',text:'\ue301',fontSize:'20px',onclick:'javascript:openDoc()'}));
		t&&(ws.titleNView.titleText=t);
		openw = plus.webview.create(id, id, ws);
		openw.addEventListener('loaded', function(){
			openw.show(as);
		}, false);
		openw.addEventListener('close', function(){
			openw=null;
		}, false);
		return openw;
	}else{
		window.open(id);
	}
	return null;
} */

// 处理点击事件
var openw=null;
function openWindow(url){
	// if(openw){//避免多次打开同一个页面
	// 	return null;
	// }
	
	openw = mui.openWindow({
		url: url,
		id: url.substring(url.lastIndexOf('/')+1, url.indexOf("?") != -1 ? url.indexOf("?") : url.length ),
		show: {
			aniShow: "pop-in",
			autoShow: true, //页面loaded事件发生后自动显示，默认为true
		},
		styles: {
			popGesture: "close",
		},
		createNew:true,
		waiting: {
			autoShow: false, //自动显示等待框，默认为true
			title: '', //等待对话框上显示的提示内容
		}
	});
	openw.addEventListener('close', function(){
		openw=null;
	}, false);
}

var Utils = {
	getUrlParam: function(name) {////获取url中的参数
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	    if (r != null) return unescape(r[2]); return null; //返回参数值
	},
	getParamFromUrl: function(url, name){
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = url.substr(url.indexOf("?")+1).match(reg);  //匹配目标参数
		if (r != null) return unescape(r[2]); return null; //返回参数值
	}
};


//var _host = "http://192.168.21.108:8083";//"http://192.168.48.82:8083";//
//var _host = "http://192.168.1.6:8083";
var _host = "http://192.168.43.253:8083";
var RequestNoSession = function (params, callback){
	var sessionId = localStorage.getItem("sessionId");
	if( !sessionId ){
		sessionId = "";
	}
	$.ajax({
		type: params.type,
	    url: _host + params.url,
	    data: params.data,
		headers: {
			'sessionId': sessionId
		},
	    success: function (r) {
			if( r.code == 0 ){
				callback.call(this, r);
			} else if(r.code == 1001){
				mui.confirm('您还未登录,请先登录', '温馨提示', ['去登录'], function(e) {
					if(e.index == 0) {
						setTimeout(function() {
							openWindow("/login.html")
						}, 200);
					}
				}, 'div');
			}else {
				mui.toast(r.msg);
			}
		}
	});
};

var Request = function (params, callback){
	var sessionId = localStorage.getItem("sessionId");
	//console.log( sessionId )
	if( !sessionId ){
		//plus.nativeUI.closeWaiting();
		mui.confirm('您还未登录,请先登录', '温馨提示', ['去登录'], function(e) {
			if(e.index == 0) {
				setTimeout(function() {
					openWindow("/login.html")
				}, 200);
			}
		}, 'div');
		
		return;
	}
	$.ajax({
		type: params.type,
	    url: _host + params.url,
	    data: params.data,
		headers: {
			'sessionId': sessionId
		},
	    success: function (r) {
			//console.log( JSON.stringify(r) )
			if(r.code == 1001 ){//未登录
				//plus.nativeUI.closeWaiting();
				//mui.toast("您还未登录，请先登录！");
				localStorage.removeItem("userInfo");
				localStorage.removeItem("sessionId");
				mui.confirm('您还未登录', '温馨提示', ['去登录'], function(e) {
					if(e.index == 0) {
						setTimeout(function() {
							openWindow("/login.html")
						}, 200);
					}
				}, 'div');
			} else {
				callback.call(this, r);
			}
		}
	});
}

function call(phone){
	plus.device.dial(phone,false);
}

//拍照并保存到相册
function getImage(callback){
	var cmr = plus.camera.getCamera();
	cmr.captureImage(function(path){
		plus.gallery.save(path, function(){
			//转换成本地图片的路径
			plus.io.resolveLocalFileSystemURL(path, function(entry){
				var imgSrc = entry.toLocalURL(); //拿到图片路径
				callback.call(this, imgSrc);//上传成功之后回调
			}, function(e){
				console.log('读取文件错误：'+e.message);
			});
		}, function(e){
			console.log('保存失败: '+JSON.stringify(e));
		});
	}, function(e){
		console.log('取消拍照');
	}, {filename:'_doc/gallery/',index:1});
}

//从相册选择一张图片
function galleryImg(callback){
	// 从相册中选择图片
	console.log('从相册中选择图片：');
    plus.gallery.pick(function(path){
		callback.call(this, path );
    }, function(e){
    	console.log('取消选择图片');
    }, {filter:'image'});
}
 
function galleryImgsMaximum(num, callback){
	num = num || 1;
	// 从相册中选择图片
    plus.gallery.pick(function(e){
    	callback.call( this, e.files );
    }, function(e){
    	console.log('取消选择图片');
    },{filter:'image',multiple:true,maximum:num,system:false,onmaxed:function(){
		plus.nativeUI.alert('最多只能选择'+num+'张图片');
    }});// 最多选择3张图片
}

//var lfs=null;// 保留上次选择图片列表
function galleryImgsSelected(selectImgArr, num, callback){
	num = num || 1;
	// 从相册中选择图片
	console.log('从相册中选择多张图片(限定最多选择3张)：');
    plus.gallery.pick(function(e){
		console.log( e.files )
    	selectImgArr=e.files;
    	callback.call( this, e.files );
    }, function(e){
		console.log( JSON.stringify(e) )
    	console.log('取消选择图片');
    },{filter:'image',multiple:true,maximum:num,selected:selectImgArr,system:false,onmaxed:function(){
		plus.nativeUI.alert('最多只能选择'+num+'张图片');
    }});// 最多选择3张图片
}

/**
 * 上传文件,
 * @param {Object} server	上传的接口路径
 * @param {Object} filePath	要上传的文件路径
 * @param {Object} quality	图片压缩比
 * @param {Object} type		上传时带的参数type
 * @param {Object} customPath	上传时带的参数,上传图片的保存路径customPath
 * @param {Object} callBack	回调函数,上传成功时,回调,并传入返回的结果
 */
function uploadImagesByQuality(server, filePath,quality, params, callBack) {
	var wt = plus.nativeUI.showWaiting("图片上传中");

	var name = filePath.substr(filePath.lastIndexOf('/') + 1);
	//压缩取得缩略图
	plus.zip.compressImage({
		src: filePath,
		dst: '_doc/' + name,
		overwrite: true,
		quality: quality
	}, function(zip) {
		var task = plus.uploader.createUpload( _host + server, {
				method: "POST"
			},
			function(t, status) { //上传完成
				if (status == 200) {
					console.log("上传成功：" + t.responseText);
					if (t.responseText != "error") {
						callBack.call( this, JSON.parse(t.responseText));
					} else {
						mui.toast("上传图片失败，请重试")
					}
					//关闭actionsheet
					wt.close(); //关闭等待提示按钮
				} else {
					mui.toast("上传图片失败，请重试")
					//关闭actionsheet
					wt.close(); //关闭等待提示按钮
				}
			}
		);
		
		//传递参数
		for( var key in params){
		    console.log(key + ": " + params[key]);
			task.addData(key, params[key]);
		}
		// 页面中要上传的 图片路径
		task.addFile(zip.target, {
			key: "file"
		});
		task.start();
	}, function(zip) {
		mui.toast('压缩失败！');
	});
}


/**
 * 上传文件,
 * @param {Object} server	上传的接口路径
 * @param {Object} filePath	要上传的文件路径
 * @param {Object} type		上传时带的参数type
 * @param {Object} customPath	上传时带的参数,上传图片的保存路径customPath
 * @param {Object} callBack	回调函数,上传成功时,回调,并传入返回的结果
 */
function uploadImages(server, filePath, params, callBack) {
	var wt = plus.nativeUI.showWaiting("图片上传中");

	var name = filePath.substr(filePath.lastIndexOf('/') + 1);
	//压缩取得缩略图
	plus.zip.compressImage({
		src: filePath,
		dst: '_doc/' + name,
		overwrite: true,
		quality: 100
	}, function(zip) {
		var task = plus.uploader.createUpload( _host + server, {
				method: "POST"
			},
			function(t, status) { //上传完成
				if (status == 200) {
					//console.log("上传成功：" + t.responseText);
					if (t.responseText != "error") {
						wt.close(); //关闭等待提示按钮
						callBack.call( this, JSON.parse(t.responseText));
					} else {
						mui.toast("上传图片失败，请重试")
					}
					//关闭actionsheet
					wt.close(); //关闭等待提示按钮
				} else {
					mui.toast("上传图片失败，请重试")
					//关闭actionsheet
					wt.close(); //关闭等待提示按钮
				}
			}
		);
		
		//传递参数
		for( var key in params){
		    ///console.log(key + ": " + params[key]);
			task.addData(key, params[key]);
		}
		// //添加其他参数
		// task.addData("type",type);
		// task.addData("path",customPath);
		// 页面中要上传的 图片路径
		task.addFile(zip.target, {
			key: "file"
		});
		task.start();
	}, function(zip) {
		mui.toast('压缩失败！');
	});
}

/**
 * 上传文件,
 * @param {Object} server	上传的接口路径
 * @param {Object} files	要上传的文件数组或单个文件
 * @param {Object} params	上传时带的参数
 * @param {Object} callback	回调函数,上传成功时,回调,并传入返回的结果
 */
function upload(server, files, params, callback){
	if( typeof files === "string" && files == ""){
		plus.nativeUI.alert('没有添加上传文件！');
		return;
	}
	
	if(files instanceof Array && files.length <= 0){
		//plus.nativeUI.alert('没有添加上传文件！');
		//return;
	}
	console.log('开始上传：');
	var wt=plus.nativeUI.showWaiting("图片上传中");
	var task=plus.uploader.createUpload(_host + server,
		{method:'POST'},
		function(t,status){ //上传完成
			plus.nativeUI.closeWaiting();
			if(status==200){
				console.log('上传成功：'+t.responseText);
				callback.call(this, t.responseText);
				//plus.storage.setItem('uploader', t.responseText);
			}else{
				console.log('上传失败：'+status);
				wt.close();
			}
		}
	);
	//传递参数
	for( var key in params){
	    console.log(key + ": " + params[key]);
		task.addData(key, params[key]);
	}
	
	var sessionId = localStorage.getItem("sessionId");
	if( sessionId ){
		task.addData("sessionId", sessionId);
	}
	
	if(files instanceof Array){
		for(var i=0;i<files.length;i++){
			var f = files[i];
			task.addFile(f, {key: f.target});
		}
	} else if( typeof files === "string" ){
		console.log("=============="+ files )
		task.addFile(files, {key:"file"});
	}
	
	task.start();
}

//短信
function smsMessage(mobile, content){
	var msg = plus.messaging.createMessage(plus.messaging.TYPE_SMS);
	msg.to = [mobile];
	msg.body = content;
	plus.messaging.sendMessage( msg );
}

function showMessages(mobile){
	//普通示例
	var userPicker = new mui.PopPicker({buttons: ["取消","发送"]});
	userPicker.setData(messageData);
	
	userPicker.show(function(items) {
		smsMessage(mobile,items[0].text);
		//返回 false 可以阻止选择框的关闭
		//return false;
	});
}

// 产生一个随机数
function getUid(){
	return Math.floor(Math.random()*100000000+10000000).toString();
}

Array.prototype.remove = function(val) { 
	var index = this.indexOf(val); 
		if (index > -1) { 
			this.splice(index, 1); 
		} 
};

Date.prototype.Format = function (fmt) { //author: meizz
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"H+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

function dateDiff(hisTime){
    if(!arguments.length) return '';
    var arg = arguments,
            now =arg[1] ? arg[1]:new Date().getTime(),
            diffValue = now - arg[0].getTime(),
            result={
                isToday:false
            },
            minute = 1000 * 60,
            hour = minute * 60,
            day = hour * 24,
            halfamonth = day * 15,
            month = day * 30,
            year = month * 12,

            _year = diffValue/year,
            _month =diffValue/month,
            _week =diffValue/(7*day),
            _day =diffValue/day,
            _hour =diffValue/hour,
            _min =diffValue/minute;

    if(new Date().toDateString() == hisTime.toDateString()){
        result.isToday = true;
    }
    if(_year>=1) result.year=parseInt(_year) + "年前";
    else if(_month>=1) result.text=parseInt(_month) + "月前";
    else if(_week>=1) result.text=parseInt(_week) + "周前";
    else if(_day>=1) result.text=parseInt(_day) +"天前";
    else if(_hour>=1) result.text=parseInt(_hour) +"小时前";
    else if(_min>=1) result.text=parseInt(_min) +"分钟前";
    else result.text="刚刚";
    return result;
}

function dateDiffYear(hisTime,nowTime){
    if(!arguments.length) return '';
    var arg = arguments,
            now =arg[1] ? arg[1]:new Date().getTime(),
            diffValue = now - arg[0].getTime(),
			minute = 1000 * 60,
			hour = minute * 60,
			day = hour * 24,
			halfamonth = day * 15,
			month = day * 30,
            year = month * 12,
            _year = diffValue / year;
			
    // if(new Date().toDateString() == hisTime.toDateString()){
    //     result.isToday = true;
    // }
    
	return Math.ceil( _year );//向上取整
}

function dateDiffDays(hisTime,nowTime){
    if(!arguments.length) return '';
    var arg = arguments,
            now =arg[1] ? arg[1]:new Date().getTime(),
            diffValue = now - arg[0].getTime(),
			minute = 1000 * 60,
			hour = minute * 60,
			day = hour * 24,
			_days = diffValue / day;
			
    // if(new Date().toDateString() == hisTime.toDateString()){
    //     result.isToday = true;
    // }
    
	return Math.ceil( _days );//向上取整
}

function copy_fun(copy){//参数copy是要复制的文本内容
    mui.plusReady(function(){
        //判断是安卓还是ios
        if(mui.os.ios){
            //ios
            var UIPasteboard = plus.ios.importClass("UIPasteboard");
            var generalPasteboard = UIPasteboard.generalPasteboard();
            //设置/获取文本内容:
            generalPasteboard.plusCallMethod({
                setValue:copy,
                forPasteboardType: "public.utf8-plain-text"
            });
            generalPasteboard.plusCallMethod({
                valueForPasteboardType: "public.utf8-plain-text"
            });
            mui.toast("已成功复制到剪贴板");
        }else{
            //安卓
            var context = plus.android.importClass("android.content.Context");
            var main = plus.android.runtimeMainActivity();
            var clip = main.getSystemService(context.CLIPBOARD_SERVICE);
            plus.android.invoke(clip,"setText",copy);
            mui.toast("已成功复制到剪贴板");
        }
    });
}

//去报名页面
function toBaoMing(params){
	//判断是否已经报名了
	Request({
		type: 'get',
		url: "/api/enroll/getMyEnroll",
		data: {}
	}, function( r ){
		if( r.code == 0 ){//已经报过名
			//判断当前的状态，若是未支付，则可以修改
			var enroll = r.result;
			//console.log( JSON.stringify(enroll) );
			if( enroll.payStatus === 1 ){//未支付,则跳转到报名页面
				//openWindow("/tab/confirm_baoming.html?id="+enroll.id);
				console.log( params )
				if( !params || params == "")
				  params = "clazzId="+enroll.schoolClazzId+"&schoolPlaceId="+enroll.schoolPlaceId+"&schoolId="+enroll.schoolId
				openWindow("/tab/baoming.html?"+params);
			} else {//已支付，则直接跳转到上传身份证页面
				var pageUrl = "";
				if( enroll.step == 2 ){//当前状态： 1-待支付 2-签订合同中 3-资料上传中 4-待审核中  5-完成
					pageUrl = "/tab/baoming2.html?id="+enroll.id;
				} else if( enroll.step == 3 ){
					pageUrl = "/tab/baoming3.html?id="+enroll.id;
				} else if( enroll.step == 4 ){
					pageUrl = "/tab/baoming4.html?id="+enroll.id;
				} else if( enroll.step == 5 ){
					pageUrl = "/tab/baoming4.html?id="+enroll.id;
				}
				console.log( pageUrl );
				openWindow(pageUrl);
			}
		} else {//没有过报名信息
			plus.webview.open('/tab/baoming.html?'+params, 'new', {}, 'pop-in', 200);
		}
	});
}

var WechatUtils = {
	open(){
		plus.runtime.openURL("weixin://")
	},
	addNewFriend(){//添加好友
		plus.runtime.openURL("weixin://dl/add")
	},
	openGzh(){//公众号
		plus.runtime.openURL("weixin://")
	}
};

// 将图片压缩转成 base64
function getBase64Image(img) {
	var canvas = document.createElement("canvas");
	var width = img.width;
	var height = img.height;
	if (width> height) {
		if (width> 500) {
			height = Math.round(height *= 500 / width);
			width = 500;
		}
	} else {
		if (height> 500) {
			width = Math.round(width *= 500 / height);
			height = 500;
		}
	}
	canvas.width = width;   /* 设置新的图片的宽度 */
	canvas.height = height; /* 设置新的图片的长度 */
	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, width, height); /* 绘图 */
	var dataURL = canvas.toDataURL("image/png", 0.8);
	var newdataurl=dataURL.substring(dataURL.indexOf(",")+1);

	return newdataurl;
}

function distance(dis){
	if( dis && typeof dis === 'number'){
		if( dis > 1000 ){
			return (dis / 1000).toFixed(2) + "km";
		} else if( dis < 50 ){
			return "< 50m"
		} else {
			return dis + "m";
		}
	}
	return "未知";
}


// 打开二维码扫描界面
function openBarcode(type){
	var param = "";
	if( type ){
		param = "?type=" + type;
	}
	createWithoutTitle('/barcode_scan.html'+param, {
		titleNView:{
			type: 'float',
			backgroundColor: 'rgba(0,119,235,0.5)',
			titleText: '扫一扫',
			titleColor: '#FFFFFF',
			autoBackButton: true,
			buttons: [{
				fontSrc: '_www/fonts/iconfont.ttf',
				text: '\ue7de',
				fontSize: '18px',
				onclick: 'javascript:scanPicture()'
			},{
				fontSrc: '_www/fonts/iconfont.ttf',
				text: '\ue60f',
				fontSize: '18px',
				onclick: 'javascript:switchFlash()'
			}]
		}
	});
}

function nFormatter(num) {
	var si = [
		{ value: 1, symbol: "" },
		{ value: 1E3, symbol: "k+" },
		{ value: 1E4, symbol: "w+" }
	];
	var rx = /\.0+$|(\.[0-9]*[1-9])0+$/;
	var i;
	for (i = si.length - 1; i > 0; i--) {
		if (num >= si[i].value) {
			break;
		}
	}
	return (num / si[i].value).toFixed(1).replace(rx, "$1") + si[i].symbol;
}

//预约入口
function ykrkFn(){
	plus.runtime.openURL("http://gab.122.gov.cn/app/m/static/page/download/download.html")
}
