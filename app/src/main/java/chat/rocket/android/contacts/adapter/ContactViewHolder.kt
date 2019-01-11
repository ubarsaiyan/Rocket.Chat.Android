package chat.rocket.android.contacts.adapter

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import chat.rocket.android.R
import chat.rocket.android.chatrooms.adapter.ViewHolder
import chat.rocket.common.model.UserStatus
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : ViewHolder<ContactItemHolder>(itemView) {

    private val resources: Resources = itemView.resources
    private val online: Drawable = resources.getDrawable(R.drawable.ic_status_online_12dp)
    private val away: Drawable = resources.getDrawable(R.drawable.ic_status_away_12dp)
    private val busy: Drawable = resources.getDrawable(R.drawable.ic_status_busy_12dp)
    private val offline: Drawable = resources.getDrawable(R.drawable.ic_status_invisible_12dp)

    override fun bindViews(data: ContactItemHolder) {
        val contact = data.data
        with(itemView) {
            contact_image_avatar.setImageURI(contact.getAvatarUrl())
            contact_name.text = contact.getName()

            if (contact.getUsername() == null) {
                contact_detail.isVisible = true
                invite_contact.isVisible = true
                chat_username.isGone = true

                // EAR > maybe we want to show this detail in the view?
                if (contact!!.isPhone()) {
                    contact_detail.text = contact!!.getPhoneNumber()
                } else {
                    contact_detail.text = contact!!.getEmailAddress()
                }

            } else {
                invite_contact.isGone = true
                chat_username.isVisible = true
                chat_username.text = "@"+contact!!.getUsername()
//                contact_status.setImageDrawable(getStatusDrawable(contact.getStatus()))
            }
        }
    }

    private fun getStatusDrawable(status: UserStatus): Drawable {
        return when(status) {
            is UserStatus.Online -> online
            is UserStatus.Away -> away
            is UserStatus.Busy -> busy
            else -> offline
        }
    }
}
