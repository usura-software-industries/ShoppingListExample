package com.urmwsk.common.populator

/**
 * //todo README
 * This is a tiny pattern/helper class that changes objects into other objects
 * while here its overkill since there is 1:1 matching between objects. It really
 * works well when there is api response that needs flattening or type changed
 * (f.e. parsed date etc.), also works pretty well with rx and kotlin functional
 * programming methods
 */
interface Populator<in SOURCE, out RESULT> {
    fun populate(s: SOURCE): RESULT
}