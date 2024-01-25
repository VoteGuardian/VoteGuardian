'use client'
import './VoteManage.scss'
import { RecoilRoot } from 'recoil'
import MenuBar from "@/components/Common/MenuBar/MenuBar"
import VoteContent from '@/components/VoteManage/VoteContent/VoteContent'

export default function VoteManage() {
    return(
        <RecoilRoot>
            <div className='flex-wrap'>
                <div className='big-wrap'>
                    <MenuBar/>
                    <div className='vote-manage-content-wrap'>  
                        <VoteContent/>
                    </div>
                </div>
            </div>
        </RecoilRoot>
    )
}