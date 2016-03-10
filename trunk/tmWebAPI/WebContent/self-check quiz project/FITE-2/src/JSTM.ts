/// <reference path="Promise.ts"/>


module jstm {
    
    import Promise = P.Promise ;
    
    export interface JSTM {
        
        guid:string;
        
        
        
        
        questionDisplay:HTMLElement;
       //
       inputExpressionSpanElement:HTMLElement;
       inputExpressionInputElement:HTMLElement;
       inputVarsSpanElement:Array<HTMLElement>;
       inputVarsInputElement:Array<HTMLElement>;
       //
       outputVarsSpanElement:Array<HTMLElement>;
       outputVarsInputElement:Array<HTMLElement>;
       outputVariableSpanElement:Array<HTMLElement>;
       outputVariableinputElement: Array<HTMLElement>;
        /** Make an HTML element that displays the state of the expression.
         *   This display will update automatically.
        */
        constructHTMLElement ():void ;
        
        constructInsertArea1():void;
        
        constructInsertArea2():void;
        /** Make a button that will move the TM state forward by
        *   the smallest interesting step.
        * @params
        *    onDone : A callback to be executed after the response from the
        *             TM arrives and the displays have been updated.
        *    onFail : A callback to be executed if the response
        *             from the TM is not HTTP response code 200.
        */
        makeGoForwardButton : (onDone ?: (jstm:JSTM) => void,
                               onFail ?: (r:P.Rejection) => void)
                            => HTMLElement ;
 
        /** Make a button that will undo the last forward step.
        * @params
        *    onDone : A callback to be executed after the response
        *             from the TM arrives and the displays have been updated.
        *    onFail : A callback to be executed if the response
        *             from the TM is not HTTP response code 200.
        */
        makeGoBackButton : (onDone? : (jstm:JSTM) => void,
                            onFail? : (r:P.Rejection) => void)
                         => HTMLElement ;

        /** Make an HTML element that displays the state of one variable.
        *    This display will update automatically.
        */
        makeVariableWatcher ?: ( varName : string ) => HTMLElement ;
        
        
        //set programText
        setprogramText(qt:string);
        
        //
        getprogramText();
        /** The current status.  See WebAPI documentation for a list */
        getStatus : () => string ;

        /** The most recent error message from the Remote TM.*/
        getMessage : () => string  ;
        
        
        
        initialize:(responseWantedFlag:string)=> Promise<JSTM> ;

        /** Compile a program. See WebAPI documentation for details.
        *   The result is a Promise object. This promise will be resolved when the 
        *   server's response arrives and has HTTP response code 200.  It will be
        *   rejected if the response code is anything else.
        */
        loadString : ( program : string, language : string ) => Promise<JSTM> ;

        /** Advance the state of the TM.  See WebAPI documentation for details.
        *   The result is a Promise object. This promise will be resolved when the 
        *   server's response arrives and has HTTP response code 200.  It will be
        *   rejected if the response code is anything else.
        */
        go : ( commandString : string ) => Promise<JSTM> ;

        /** Same as go("f") ;
        */
        goForward : () => Promise<JSTM> ;

        /** Undo the last advance of the remote TM.
        *   The result is a Promise object. This promise will be resolved when the 
        *   server's response arrives and has HTTP response code 200.  It will be
        *   rejected if the response code is anything else.
        */
        goBack : () => Promise<JSTM> ; 
    }
}