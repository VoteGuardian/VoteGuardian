import VoteList from '@/components/VoteManage/VoteList/VoteList'
import './VoteManage.scss'
import MenuBar from "@/components/VoteManage/VoteManageMenu/MenuBar"
// import VoteOne from "@/components/VoteManage/VoteList/VoteOne"

export default function VoteManage() {
    return(
        <div className='flex-wrap'>
            <div className='big-wrap'>
                <MenuBar/>
            </div>
        </div>
    )
}