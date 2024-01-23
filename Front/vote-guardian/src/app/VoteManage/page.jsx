
import './VoteManage.scss'
import VoteList from '@/components/VoteManage/VoteContent/VoteList/VoteList'
import MenuBar from "@/components/Common/MenuBar/MenuBar"
import VoteManageHeader from '@/components/VoteManage/VoteContent/VoteManageHeader'


export default function VoteManage() {
    return(
        <div className='flex-wrap'>
            <div className='big-wrap'>
                <MenuBar/>
                <div className='vote-manage-content-wrap'>  
                    <VoteManageHeader/>
                    <VoteList/>
                </div>
            </div>
        </div>
    )
}