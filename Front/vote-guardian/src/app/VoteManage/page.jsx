'use client'
import './VoteManage.scss'
import { RecoilRoot } from 'recoil'
import MenuBar from "@/components/Common/MenuBar/MenuBar"
import VoteContent from '@/components/VoteManage/VoteManage'
import { Mobile, PC, Tablet } from '@/hooks/useResize';
import Pagination from "@/components/Common/Pagiantion/Pagination";
export default function VoteManage() {
    return(
        <RecoilRoot>
            <div className='flex-wrap'>
                <PC>
                    <div className='pc-wrap'>
                        <MenuBar/>
                        <div>
                            <div className='vote-manage-flex'>
                                <div className='vote-manage-wrap'>  
                                    <VoteContent/>
                                </div>
                            </div>
                            <Pagination/>
                        </div>
                        
                    </div>
                </PC>
                <Tablet>
                    <div className='tablet-wrap'>
                        <MenuBar/>
                        <div className='vote-manage-flex'>
                            <div className='vote-manage-wrap'>  
                                <VoteContent/>
                            </div>
                        </div>
                        <Pagination/>
                    </div>
                </Tablet>
                <Mobile>
                    <div className='mobile-wrap'>
                        <MenuBar/>
                        <div>
                            <div className='vote-manage-flex'>
                                <div className='vote-manage-wrap'>  
                                    <VoteContent/>
                                </div>
                            </div>
                            <Pagination/>
                        </div>
                        
                    </div>
                </Mobile>
                {/* <Mobile>
                    <div className='mobile-wrap'>
                        <MenuBar/>
                        <div className='vote-manage-flex'>
                            <div className='vote-manage-wrap'>  
                                <VoteContent/>
                            </div>
                        </div>
                        <Pagination/>
                    </div>
                </Mobile> */}
            </div>
        </RecoilRoot>
    )
}