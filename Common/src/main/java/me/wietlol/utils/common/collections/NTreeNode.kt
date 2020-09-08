package me.wietlol.utils.common.collections

class NTreeNode<T>(
	val value: T,
	val parentNode: NTreeNode<T>? = null,
	children: List<NTreeNode<T>> = emptyList()
)
{
	private val myChildren: MutableList<NTreeNode<T>> = children.toMutableList()
	val children: List<NTreeNode<T>>
		get() = myChildren
	
	init
	{
		parentNode?.addChild(this)
	}
	
	private fun addChild(child: NTreeNode<T>)
	{
		myChildren.add(child)
	}
	
	fun sequenceToRoot(): Sequence<T> =
		parentSequenceToRoot()?.plus(value)
			?: sequenceOf(value)
	
	private fun parentSequenceToRoot(): Sequence<T>? =
		parentNode?.sequenceToRoot()
}
