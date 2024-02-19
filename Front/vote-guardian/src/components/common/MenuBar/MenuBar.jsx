'use client'
import './MenuBar.scss';
import PcDropDown from "./PcDropDown";
import MobileDropDown from './MobileDropDown';
import { Mobile, PC, Tablet } from '@/hooks/useResize';

export default function MenuBar() {
    return(
        <>
            <PC>
                <div className='pc-bar-wrap'>
                    <div className='menu-header'>
                        <p className='menu-header-title'>투표 가디언</p>
                        <PcDropDown/>
                    </div>
                </div>
            </PC>
            <Tablet>
                <div className='mobile-bar-wrap'>
                    <p className='mobile-title'>투표 가디언</p>
                    <MobileDropDown/>
                </div>
            </Tablet>
            <Mobile>
                <div className='mobile-bar-wrap'>
                    <p className='mobile-title'>투표 가디언</p>
                    <MobileDropDown/>
                </div>
            </Mobile>
        </>
        
    )
}